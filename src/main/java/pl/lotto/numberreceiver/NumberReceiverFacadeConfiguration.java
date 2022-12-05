package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumberReceiverFacadeConfiguration {

    @Bean
    Clock clock(){
        return Clock.systemUTC();
    }
    @Bean
    NumberReceiverFacade numberReceiverFacade(Clock clock, NumbersInputRepository repository) {
        NumberInputValidator validator = new NumberInputValidator();
        DrawDatesFinder drawDatesFinder = new DrawDatesFinder(clock);
        return new NumberReceiverFacade(validator, drawDatesFinder, repository);
    }

    NumberReceiverFacade createModuleForTests(Clock clock, NumbersInputRepository repository) {
        NumberInputValidator validator = new NumberInputValidator();
        DrawDatesFinder drawDatesFinder = new DrawDatesFinder(clock);
        return new NumberReceiverFacade(validator, drawDatesFinder, repository);
    }

}
