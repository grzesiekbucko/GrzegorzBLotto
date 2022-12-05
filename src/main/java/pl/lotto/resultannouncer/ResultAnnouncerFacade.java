package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.TicketResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResultAnnouncerFacade {

    ResultCheckerFacade resultCheckerFacade;
    ResultAnnouncerMessage resultAnnouncerMessage;


    public ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade, ResultAnnouncerMessage resultAnnouncerMessage) {
        this.resultCheckerFacade = resultCheckerFacade;
        this.resultAnnouncerMessage = resultAnnouncerMessage;
    }

    public ResultAnnouncerDto checkTicket(String id) {
        TicketResult ticketResult = resultCheckerFacade.checkWinningsForGivenTicketId(id);
        return resultAnnouncerMessage.summaryMessage(ticketResult);

    }

    public List<TicketResult> checkTicketByDate(LocalDateTime date) {
        return resultCheckerFacade.checkWinningsForGivenDate(date);

    }


}
