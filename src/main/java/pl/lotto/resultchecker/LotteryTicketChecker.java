package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

class LotteryTicketChecker {


    static List<TicketResult> checkAllTicket(List<NumbersGeneratorResultDto> draws, List<TicketDto> tickets) {
        List<TicketResult> ticketResultDto = new ArrayList<>();
        for (TicketDto ticket : tickets) {
            LocalDateTime ticketDate = ticket.drawDate();
            for (NumbersGeneratorResultDto draw : draws) {
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
}
