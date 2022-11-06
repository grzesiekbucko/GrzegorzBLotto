package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static pl.lotto.numberreceiver.NumberReceiverResultDto.failure;
import static pl.lotto.numberreceiver.NumberReceiverResultDto.success;

public class NumberReceiverFacade {

    private final NumberInputValidator validator;
    private final DrawDatesFinder drawDatesFinder;

    private final NumbersInputRepository repository;

    public NumberReceiverFacade(NumberInputValidator validator, DrawDatesFinder drawDatesFinder) {
        this.validator = validator;
        this.drawDatesFinder = drawDatesFinder;
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        NumberReciverInputError validate = validator.validate(numbersFromUser);
        if (validate.isError()) {
            return failure(validate.getMessage());
        }
        LocalDateTime nearestDrawDate = drawDatesFinder.upcomingDrawDate();
        UUID uuid = UUID.randomUUID();
        String userLotteryId = uuid.toString();
        repository.save();
        return success(nearestDrawDate, userLotteryId);
    }



}
