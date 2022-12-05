package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NumbersInputRepository extends MongoRepository<LotteryTicket, String> {

    LotteryTicket save(LotteryTicket lotteryTicket);

    List<LotteryTicket> findAllByNearestDrawDate(LocalDateTime nearestDrawDate);

    List<LotteryTicket> findAll();


}
