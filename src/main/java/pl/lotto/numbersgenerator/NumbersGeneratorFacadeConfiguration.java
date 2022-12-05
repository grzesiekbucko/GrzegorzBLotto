package pl.lotto.numbersgenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;

@Configuration
public
class NumbersGeneratorFacadeConfiguration {

    @Bean
    public NumbersGeneratorFacade numbersGeneratorFacade(Clock clock, NumbersGeneratorRepository repository) {
        Draw draw = new Draw(clock);
        return new NumbersGeneratorFacade(draw, repository);
    }

    NumbersGeneratorFacade createModuleForTests(Clock clock, NumbersGeneratorRepository repository) {
        Draw draw = new Draw(clock);
        return new NumbersGeneratorFacade(draw, repository);
    }



}
