package pl.lotto.resultannouncer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Configuration
public class ResultAnnouncerFacadeConfiguration {

    @Bean
    ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        ResultAnnouncerMessage resultAnnouncerMessage = new ResultAnnouncerMessage();
        return new ResultAnnouncerFacade(resultCheckerFacade, resultAnnouncerMessage);
    }

}
