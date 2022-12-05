package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketsForGivenDateDto;
import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

class LotteryTicketChecker {


    List<TicketResult> checkAllTicket(List<NumbersGeneratorResultDto> winningNumbers, List<TicketDto> ticketsFromUsers) {
        List<TicketResult> ticketResultDto = new ArrayList<>();
        for (TicketDto ticket : ticketsFromUsers) {
            LocalDateTime ticketDate = ticket.drawDate();
            for (NumbersGeneratorResultDto draw : winningNumbers) {
                if (ticketDate.isEqual(draw.drawDate())) {
                    List<Integer> common = ticket.numbers()
                            .stream()
                            .filter(draw.numbers()::contains)
                            .collect(toList());
                    ticketResultDto.add(new TicketResult(ticket.uuid(), draw.drawDate(), draw.numbers(), ticket.numbers(), common));
                }
            }
        }
        return ticketResultDto;
    }

    List<TicketResult> checkAllTicketOnGivenDate(NumbersGeneratorResultDto winningNumbers, List<TicketsForGivenDateDto> ticketsFromUsers) {
        List<TicketResult> ticketResultDto = new ArrayList<>();
        for (TicketsForGivenDateDto ticket : ticketsFromUsers) {
            List<Integer> common = ticket.userNumbers()
                    .stream()
                    .filter(winningNumbers.numbers()::contains)
                    .collect(toList());
            ticketResultDto.add(new TicketResult(ticket.uuid(), winningNumbers.drawDate(), winningNumbers.numbers(), ticket.userNumbers(), common));
        }
        return ticketResultDto;
    }


}
