package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ResultCheckerFacadeTestImpl implements ResultCheckerRepository {
    Map<UUID, TicketResult> database = new ConcurrentHashMap<>();

    @Override
    public void insertAll(List<TicketResult> list) {
        list.stream()
                .map(ticket -> database.put(ticket.getTickedId(), ticket));
    }

    @Override
    public List<TicketResult> findAllByDate(LocalDateTime date) {
        return database.values()
                .stream()
                .filter(ticket -> ticket.getLotteryDate().isEqual(date))
                .collect(Collectors.toList());
    }

    @Override
    public TicketResult findById(UUID id) {
        return database.get(id);
    }
}
