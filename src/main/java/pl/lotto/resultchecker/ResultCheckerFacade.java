package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.numbersgenerator.dto.NumbersGeneratorResultDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ResultCheckerFacade {

    NumbersGeneratorFacade generator;
    NumberReceiverFacade receiverFacade;
    ResultCheckerRepository repository;

    ResultCheckerFacade(NumbersGeneratorFacade generator, NumberReceiverFacade receiverFacade, ResultCheckerRepository repository) {
        this.generator = generator;
        this.receiverFacade = receiverFacade;
        this.repository = repository;
    }

    public List<TicketResult> checkAllWinnings() {
        List<NumbersGeneratorResultDto> draws = generator.getWinningNumbers();
        List<TicketDto> tickets = receiverFacade.retrieveAllTicket();
        List<TicketResult> list = LotteryTicketChecker.checkAllTicket(draws, tickets);
        repository.insertAll(list);
        return list;
    }

    public List<TicketResult> checkWinningsForGivenDate(LocalDateTime date) {
        return repository.findAllByDate(date);
    }

    public TicketResult checkWinningsForGivenTicketId(UUID id) {
        return repository.findById(id);
    }
}
