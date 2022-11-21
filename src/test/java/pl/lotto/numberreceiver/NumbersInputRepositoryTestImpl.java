package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NumbersInputRepositoryTestImpl implements NumbersInputRepository {

    Map<UUID, LotteryTicket> database = new ConcurrentHashMap<>();

    @Override
    public void save(LotteryTicket lotteryTicket) {
        database.put(lotteryTicket.uuid, lotteryTicket);
    }

    @Override
    public List<LotteryTicket> findAllByDate(LocalDateTime date) {
        return database.values()
                .stream()
                .filter(lotteryTicket -> lotteryTicket.nearestDrawDate.isEqual(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<LotteryTicket> findAll() {
        return new ArrayList<>(database.values());
    }

}
