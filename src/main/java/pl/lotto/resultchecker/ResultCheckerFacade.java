package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketsForGivenDateDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;

import java.time.LocalDateTime;
import java.util.List;

public class ResultCheckerFacade {

    NumbersGeneratorFacade generator;
    NumberReceiverFacade receiverFacade;
    ResultCheckerRepository repository;
    LotteryTicketChecker lotteryTicketChecker;

    ResultCheckerFacade(NumbersGeneratorFacade generator, NumberReceiverFacade receiverFacade, ResultCheckerRepository repository, LotteryTicketChecker lotteryTicketChecker) {
        this.generator = generator;
        this.receiverFacade = receiverFacade;
        this.repository = repository;
        this.lotteryTicketChecker = lotteryTicketChecker;
    }

    public void checkAllWinnings() {
        List<NumbersGeneratorResultDto> draws = generator.getWinningNumbers();
        List<TicketDto> tickets = receiverFacade.retrieveAllTicket();
        List<TicketResult> list = lotteryTicketChecker.checkAllTicket(draws, tickets);
        repository.insert(list);
    }

    public List<TicketResult> checkWinningsForGivenDate(LocalDateTime date) {
        NumbersGeneratorResultDto draw = generator.getWinningNumbersByDate(date);
        List<TicketsForGivenDateDto> tickets = receiverFacade.retrieveAllTicketForGivenDate(date);
        List<TicketResult> list = lotteryTicketChecker.checkAllTicketOnGivenDate(draw, tickets);
        repository.insert(list);
        return list;
    }

    public TicketResult checkWinningsForGivenTicketId(String id) {
        return repository.findByTickedId(id).orElse(null);
    }
}
