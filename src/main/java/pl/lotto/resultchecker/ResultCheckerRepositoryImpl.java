package pl.lotto.resultchecker;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
class ResultCheckerRepositoryImpl implements  ResultCheckerRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    ResultCheckerRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insertAll(List<TicketResult> tickets){
        mongoTemplate.insert(tickets, "ticket_result");

    }
}
