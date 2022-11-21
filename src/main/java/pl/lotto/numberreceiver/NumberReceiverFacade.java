package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketsForGivenDateDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static pl.lotto.numberreceiver.dto.NumberReceiverResultDto.failure;
import static pl.lotto.numberreceiver.dto.NumberReceiverResultDto.success;

public class NumberReceiverFacade {

    private final NumberInputValidator validator;
    private final DrawDatesFinder drawDatesFinder;
    private final NumbersInputRepository repository;

    NumberReceiverFacade(NumberInputValidator validator, DrawDatesFinder drawDatesFinder, NumbersInputRepository repository) {
        this.validator = validator;
        this.drawDatesFinder = drawDatesFinder;
        this.repository = repository;
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        NumberReciverInputError validate = validator.validate(numbersFromUser);
        if (validate.isError()) {
            return failure(validate.getMessage());
        }
        LocalDateTime nearestDrawDate = drawDatesFinder.upcomingDrawDate();
        UUID uuid = UUID.randomUUID();
        String userLotteryId = uuid.toString();
        LotteryTicket lotteryTicket = new LotteryTicket(uuid, nearestDrawDate, numbersFromUser);
        repository.save(lotteryTicket);
        return success(nearestDrawDate, userLotteryId);
    }

    public List<List<Integer>> retrieveAllNumbersForGivenDate(LocalDateTime date) {
        List<LotteryTicket> result = repository.findAllByDate(date);
        return result.stream()
                .map(lotteryTicket -> lotteryTicket.numbersFromUser)
                .toList();
    }

    public List<TicketsForGivenDateDto> retrieveAllTicketForGivenDate(LocalDateTime date) {
        return repository.findAllByDate(date)
                .stream()
                .map(LotteryTicket -> new TicketsForGivenDateDto(LotteryTicket.uuid, LotteryTicket.numbersFromUser))
                .toList();
    }

    public List<TicketDto> retrieveAllTicket() {
        return repository.findAll()
                .stream()
                .map(LotteryTicket -> new TicketDto(LotteryTicket.uuid, LotteryTicket.nearestDrawDate, LotteryTicket.numbersFromUser))
                .toList();
    }


}
