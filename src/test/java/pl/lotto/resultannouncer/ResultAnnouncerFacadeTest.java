package pl.lotto.resultannouncer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.TicketResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class ResultAnnouncerFacadeTest {

    @Test
    void Should_return_message_when_ticket_id_is_given() {
//        given
        UUID uuid = UUID.randomUUID();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(1, 2, 31, 4, 53, 64);
        List<Integer> hitNumbers = List.of(1, 31, 53, 64);

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

//        when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade);

//        then
        ResultAnnouncerDto result = facade.checkTicket(uuid);
        assertThat(result.getMessage()).isEqualTo("Congratulations!! You hit 4 numbers");
    }

    @Test
    void Should_return_hit_numbers_when_ticket_id_is_given() {
//        given
        UUID uuid = UUID.randomUUID();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(1, 2, 31, 4, 53, 64);
        List<Integer> hitNumbers = List.of(1, 31, 53, 64);

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

//        when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade);

//        then
        ResultAnnouncerDto result = facade.checkTicket(uuid);
        assertThat(result.getHitNumbers()).isEqualTo(hitNumbers);
    }

    @Test
    void Should_return_0_when_no_numbers_were_hit() {
//        given
        UUID uuid = UUID.randomUUID();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(11, 2, 3, 4, 5, 6);
        List<Integer> hitNumbers = List.of();

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

//        when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade);

//        then
        ResultAnnouncerDto result = facade.checkTicket(uuid);
        assertThat(result.getHitNumbers().size()).isEqualTo(0);
    }

}