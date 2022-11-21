package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

public interface NumbersInputRepository {

    void save(LotteryTicket lotteryTicket);

    List<LotteryTicket> findAllByDate(LocalDateTime date);

    List<LotteryTicket> findAll();

}
