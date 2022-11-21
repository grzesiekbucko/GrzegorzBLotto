package pl.lotto.numberreceiver;

import java.time.Clock;

public class NumberReceiverFacadeConfiguration {

    NumberReceiverFacade createModuleForTests(Clock clock, NumbersInputRepository repository) {
        NumberInputValidator validator = new NumberInputValidator();
        DrawDatesFinder drawDatesFinder = new DrawDatesFinder(clock);
        return new NumberReceiverFacade(validator, drawDatesFinder, repository);
    }


}
