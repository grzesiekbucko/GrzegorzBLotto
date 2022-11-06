package pl.lotto.numberreceiver;

import java.time.LocalDateTime;

public record NumberReceiverResultDto(
        LocalDateTime drawTime,
        String userLotteryId,
        boolean error,
        String message
) {

//    private LocalDateTime drawTime;
//    private String userLotteryId;
//
//    private boolean error;
//    private String message;

//    public NumberReceiverResultDto(NumberReciverInputError error, LocalDateTime drawTime, String userLotteryId) {
//        this.error = error;
//        this.drawTime = drawTime;
//        this.userLotteryId = userLotteryId;
//    }


    public static NumberReceiverResultDto success(LocalDateTime nearestDrawDate, String userLotteryId) {
        return new NumberReceiverResultDto(nearestDrawDate, userLotteryId, false, "all good");
    }

    public static NumberReceiverResultDto failure(String message) {
        return new NumberReceiverResultDto(null, null, true, message);
    }

//    private NumberReceiverResultDto(LocalDateTime drawTime, String userLotteryId, boolean error, String message) {
//        this.drawTime = drawTime;
//        this.userLotteryId = userLotteryId;
//        this.error = error;
//        this.message = message;
//    }

//    public LocalDateTime getDrawTime() {
//        return drawTime;
//    }
//
//    public boolean isError() {
//        return error;
//    }
//
//    public String getMessage() {
//        return message;
//    }

    //    public NumberReceiverResultDto(NumberReciverInputError error) {
//        this.error = error;
//    }
//
//    LocalDateTime getDrawTime() {
//        return drawTime;
//    }
//
////    NumberReciverInputError getError() {
////        return error;
////    }
//
//    void setError(NumberReciverInputError error) {
//        this.error = error;
//    }

//    public String getUserLotteryId() {
//        return userLotteryId;
//    }
//
//    public void setUserLotteryId(String userLotteryId) {
//        this.userLotteryId = userLotteryId;
//    }


}
