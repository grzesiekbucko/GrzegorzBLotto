package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResultCheckerRepository extends MongoRepository<TicketResult, String>, ResultCheckerRepositoryCustom {

    void insertAll(List<TicketResult> list);

    List<TicketResult> findByLotteryDate(LocalDateTime lotteryDate);

    @Query
    Optional<TicketResult> findByTickedId(String tickedId);

}
