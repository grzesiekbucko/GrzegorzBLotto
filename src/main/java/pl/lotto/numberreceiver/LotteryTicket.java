package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

class LotteryTicket {

    UUID uuid;
    LocalDateTime nearestDrawDate;
    List<Integer> numbersFromUser;

    LotteryTicket(UUID uuid, LocalDateTime nearestDrawDate, List<Integer> numbersFromUser) {
        this.uuid = uuid;
        this.nearestDrawDate = nearestDrawDate;
        this.numbersFromUser = numbersFromUser;
    }
}
