package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.TicketResult;

class ResultAnnouncerMessage {

    static ResultAnnouncerDto summaryMessage(TicketResult ticketResult) {
        int hitNumbers = ticketResult.getHitNumbers().size();
        String message = "";
        if (hitNumbers == 6) {
            message = "Congratulations!! You hit the jackpot!!";
        }
        if (hitNumbers >= 1 && hitNumbers <= 5) {
            message = "Congratulations!! You hit " + hitNumbers + " numbers";
        }
        if (hitNumbers == 0) {
            message = "You didn't hit any numbers. Try again";
        }
        return ResultAnnouncerDto.builder()
                .tickedId(ticketResult.getTickedId())
                .lotteryDate(ticketResult.getLotteryDate())
                .winningNumbers(ticketResult.getWinningNumbers())
                .userNumbers(ticketResult.getUserNumbers())
                .hitNumbers(ticketResult.getHitNumbers())
                .message(message)
                .build();
    }
}
