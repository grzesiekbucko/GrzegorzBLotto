package pl.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.NumbersInputRepository;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.NumbersGeneratorFacadeConfiguration;
import pl.lotto.numbersgenerator.NumbersGeneratorRepository;
import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.TicketResult;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = LottoGameApplication.class)
@AutoConfigureMockMvc
@Testcontainers
public class ApplicationRunTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    NumberReceiverFacade numberReceiverFacade;
    @Autowired
    NumbersInputRepository repository;
    @Autowired
    NumbersGeneratorFacade numbersGeneratorFacade;
    @Autowired
    ResultCheckerFacade resultCheckerFacade;
    @Autowired
    NumbersGeneratorRepository numbersGeneratorRepository;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @BeforeAll
    static void initial() {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    private static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void happy_path() throws Exception {

        // STEP 1 user gave numbers
        // given
        MockHttpServletRequestBuilder postInputNumbers = MockMvcRequestBuilders.post("/inputNumbers");
        // when
        ResultActions resultForInputNumbers = mockMvc.perform(postInputNumbers
                .content(asJsonString(Map.of("numbers", List.of(1, 2, 3, 4, 5, 6))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // then
        resultForInputNumbers.andExpect(status().isOk());
        String contentAsString = resultForInputNumbers.andReturn().getResponse().getContentAsString();
        assertThat(contentAsString).contains("\"drawTime\":\"2022-12-10T12:00:00\"");
        assertThat(contentAsString).contains("\"userLotteryId\"");
        assertThat(contentAsString).contains("\"error\":false");
        assertThat(contentAsString).contains("\"message\":\"all good\"");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        NumberReceiverResultDto NumbersGeneratorResultDto = mapper.readValue(contentAsString, NumberReceiverResultDto.class);

        String id = NumbersGeneratorResultDto.userLotteryId();


        // STEP 2 user want to know if won before draw date
        // given
        MockHttpServletRequestBuilder getWinners = MockMvcRequestBuilders.get("/winners/" + id);
        // when
        ResultActions resultFromGetWinners = mockMvc.perform(getWinners
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // then
        resultFromGetWinners.andExpect(status().isOk());
        String performAsString = resultFromGetWinners.andReturn().getResponse().getContentAsString();
        assertThat(performAsString).contains("\"message\":\"Incorrect coupon id or the draw has not yet taken place\"");


        // STEP 3 system generates random winning number
        // given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 12, 10, 12, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
        NumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration().numbersGeneratorFacade(clock, numbersGeneratorRepository);
        // when
        NumbersGeneratorResultDto numbersGeneratorResultDto = numbersGeneratorFacade.generateNumbers();
        // then
        LocalDateTime drawDate = numbersGeneratorResultDto.drawDate();
        List<Integer> numbers = numbersGeneratorResultDto.numbers();
        assertThat(numbers).isNotNull();
        assertThat(drawDate).hasHour(12);


        // STEP 4 system checks all tickets on given day
        // given
        // when
        List<TicketResult> results = resultCheckerFacade.checkWinningsForGivenDate(drawDate);
        // then
        assertThat(results).isNotNull();


        // STEP 5 user want to know if won after draw date
        // given
        MockHttpServletRequestBuilder getWinners2 = MockMvcRequestBuilders.get("/winners/" + id);
        // when
        ResultActions resultFromGetWinners2 = mockMvc.perform(getWinners2
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // then
        resultFromGetWinners2.andExpect(status().isOk());
        String contentAsString2 = resultFromGetWinners2.andReturn().getResponse().getContentAsString();
        assertThat(contentAsString2).contains("\"tickedId\":\"" + id + "\"");
        assertThat(contentAsString2).contains("\"userNumbers\":[1,2,3,4,5,6]");

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
