package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.util.UUID;

public class ResultAnnouncerFacade {

    ResultCheckerFacade resultCheckerFacade;

    public ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public ResultAnnouncerDto checkTicket(UUID id) {
        return ResultAnnouncerMessage.summaryMessage(resultCheckerFacade.checkWinningsForGivenTicketId(id));

    }



}
