package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ResultCheckerRepository {

    void insertAll(List<TicketResult> list);

    List<TicketResult> findAllByDate(LocalDateTime date);

    TicketResult findById(UUID id);
}
