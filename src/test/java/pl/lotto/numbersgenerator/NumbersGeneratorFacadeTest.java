package pl.lotto.numbersgenerator;

import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NumbersGeneratorFacadeTest {

    @ParameterizedTest
    @MethodSource("createDatesForDraw")
    @DisplayName("should return generated numbers on specific day")
    public void should_return_generated_numbers_on_specific_day(LocalDateTime lotteryDate) {
        //given
        Clock clock = Clock.fixed(lotteryDate.toInstant(ZoneOffset.of("+1")), ZoneId.of("+1"));
        NumbersGeneratorRepositoryTestImpl repositoryTest = new NumbersGeneratorRepositoryTestImpl();
        NumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration().createModuleForTests(clock, repositoryTest);
        //when
        NumbersGeneratorResultDto winningNumbers = numbersGeneratorFacade.generateNumbers();
        //then
        System.out.println(winningNumbers.drawDate() + " " + winningNumbers.numbers());
        assertThat(winningNumbers.numbers()).isNotEmpty();
    }

    private static Stream<Arguments> createDatesForDraw() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2022, 11, 5, 12, 0)),
                Arguments.of(LocalDateTime.of(2022, 11, 11, 12, 0)),
                Arguments.of(LocalDateTime.of(2022, 11, 12, 12, 0)),
                Arguments.of(LocalDateTime.of(2022, 11, 19, 12, 0))
        );
    }


}
