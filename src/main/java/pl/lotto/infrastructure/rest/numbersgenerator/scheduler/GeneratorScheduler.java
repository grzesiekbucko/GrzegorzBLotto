package pl.lotto.infrastructure.rest.numbersgenerator.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class GeneratorScheduler {
    NumbersGeneratorFacade numbersGeneratorFacade;
    ResultCheckerFacade resultCheckerFacade;


    @Scheduled(cron = "0 0 12 * * SAT")
    public void startLotteryAndCheckForWinnings() throws InterruptedException {
        LocalDateTime drewDate = numbersGeneratorFacade.generateNumbers().drawDate();
        Thread.sleep(10000);
        resultCheckerFacade.checkWinningsForGivenDate(drewDate);
    }

}
