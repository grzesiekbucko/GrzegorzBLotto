package pl.lotto.resultannouncer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.TicketResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest {

    @Test
    void Should_return_jackpot_message_when_hit_numbers_is_6() {
        // given
        String uuid = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> hitNumbers = List.of(1, 20, 31, 42, 53, 64);

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

        // when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade, new ResultAnnouncerMessage());
        ResultAnnouncerDto result = facade.checkTicket(uuid);

        // then
        assertThat(result.getMessage()).isEqualTo("Congratulations!! You hit the jackpot!!");
    }

    @Test
    void Should_return_a_message_with_the_numbers_hit() {
        // given
        String uuid = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(1, 2, 31, 4, 53, 64);
        List<Integer> hitNumbers = List.of(1, 31, 53, 64);

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

        // when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade, new ResultAnnouncerMessage());
        ResultAnnouncerDto result = facade.checkTicket(uuid);

        // then
        assertThat(result.getMessage()).isEqualTo("Congratulations!! You hit 4 numbers");
    }

    @Test
    void Should_return_message_when_no_numbers_were_hit() {
        // given
        String uuid = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(11, 2, 3, 4, 5, 6);
        List<Integer> hitNumbers = List.of();

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

        // when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade, new ResultAnnouncerMessage());
        ResultAnnouncerDto result = facade.checkTicket(uuid);

        // then
        assertThat(result.getMessage()).isEqualTo("You didn't hit any numbers. Try again");
    }

    @Test
    void Should_throw_illegal_state_when_hit_number_is_seven() {
        // given
        String uuid = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(1, 2, 31, 4, 53, 64);
        List<Integer> hitNumbers = List.of(1, 31, 53, 64, 20, 11, 15);

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

        // when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade, new ResultAnnouncerMessage());
        Exception exception = assertThrows(IllegalStateException.class, () -> facade.checkTicket(uuid));

        // then
        assertThat(exception.getMessage()).isEqualTo("hit numbers was: 7");
    }

    @Test
    void Should_return_hit_numbers_when_ticket_id_is_given() {
//        given
        String uuid = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(1, 2, 31, 4, 53, 64);
        List<Integer> hitNumbers = List.of(1, 31, 53, 64);

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

//        when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade, new ResultAnnouncerMessage());

//        then
        ResultAnnouncerDto result = facade.checkTicket(uuid);
        assertThat(result.getHitNumbers()).isEqualTo(hitNumbers);
    }

    @Test
    void Should_return_0_when_no_numbers_were_hit() {
//        given
        String uuid = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);
        List<Integer> winningNumbers = List.of(1, 20, 31, 42, 53, 64);
        List<Integer> userNumbers = List.of(11, 2, 3, 4, 5, 6);
        List<Integer> hitNumbers = List.of();

        TicketResult ticket = new TicketResult(uuid, drawDate, winningNumbers, userNumbers, hitNumbers);
        ResultCheckerFacade resultCheckerFacade = Mockito.mock(ResultCheckerFacade.class);

//        when
        when(resultCheckerFacade.checkWinningsForGivenTicketId(uuid)).thenReturn(ticket);
        ResultAnnouncerFacade facade = new ResultAnnouncerFacade(resultCheckerFacade, new ResultAnnouncerMessage());

//        then
        ResultAnnouncerDto result = facade.checkTicket(uuid);
        assertThat(result.getHitNumbers().size()).isEqualTo(0);
    }

}
