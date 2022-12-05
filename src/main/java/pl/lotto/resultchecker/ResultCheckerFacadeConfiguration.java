package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numbersgenerator.NumbersGeneratorFacade;

@Configuration
class ResultCheckerFacadeConfiguration {

    @Bean
    ResultCheckerFacade resultCheckerFacade(NumbersGeneratorFacade generator, NumberReceiverFacade receiverFacade, ResultCheckerRepository repository) {
        LotteryTicketChecker lotteryTicketChecker = new LotteryTicketChecker();
        return new ResultCheckerFacade(generator, receiverFacade, repository, lotteryTicketChecker);
    }


}
