package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.lotto.numberreceiver.*;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketsForGivenDateDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ResultCheckerFacadeTest {

    @Test
    public void Should_return_4_as_the_number_of_numbers_hit() {
        // given
        ResultCheckerFacadeTestImpl repository = new ResultCheckerFacadeTestImpl();
        NumbersGeneratorFacade generator = Mockito.mock(NumbersGeneratorFacade.class);

        // Draw numbers and date
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 11, 0);
        NumbersGeneratorResultDto draw = new NumbersGeneratorResultDto(drawDate, winningNumbers);

        when(generator.getWinningNumbersByDate(drawDate)).thenReturn(draw);

        // Users lottery tickets
        NumberReceiverFacade receiverFacade = Mockito.mock(NumberReceiverFacade.class);
        String uuid = UUID.randomUUID().toString();
        List<Integer> userNumbers = List.of(1, 2, 31, 4, 53, 64);
        LocalDateTime ticketDate = LocalDateTime.of(2022, 11, 19, 11, 0);

        List<TicketsForGivenDateDto> ticketList = List.of(new TicketsForGivenDateDto(uuid, userNumbers));
        when(receiverFacade.retrieveAllTicketForGivenDate(ticketDate)).thenReturn(ticketList);

        // when
        ResultCheckerFacade facade = new ResultCheckerFacade(generator, receiverFacade, repository, new LotteryTicketChecker());
        List<TicketResult> result = facade.checkWinningsForGivenDate(drawDate);

        //then
        assertThat(result.get(0).getHitNumbers().size()).isEqualTo(4);

    }
}
