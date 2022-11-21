package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;

public record NumberReceiverResultDto(
        LocalDateTime drawTime,
        String userLotteryId,
        boolean error,
        String message
) {

    public static NumberReceiverResultDto success(LocalDateTime nearestDrawDate, String userLotteryId) {
        return new NumberReceiverResultDto(nearestDrawDate, userLotteryId, false, "all good");
    }

    public static NumberReceiverResultDto failure(String message) {
        return new NumberReceiverResultDto(null, null, true, message);
    }

}
