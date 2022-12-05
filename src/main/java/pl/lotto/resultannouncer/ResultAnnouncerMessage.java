package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.TicketResult;

class ResultAnnouncerMessage {

    ResultAnnouncerDto summaryMessage(TicketResult ticketResult) {
        try {
            int hitNumbers = ticketResult.getHitNumbers().size();
            String message = createMessage(hitNumbers);
            return ResultAnnouncerDto.builder()
                    .tickedId(ticketResult.getTickedId())
                    .lotteryDate(ticketResult.getLotteryDate())
                    .winningNumbers(ticketResult.getWinningNumbers())
                    .userNumbers(ticketResult.getUserNumbers())
                    .hitNumbers(ticketResult.getHitNumbers())
                    .message(message)
                    .build();
        } catch (NullPointerException e) {
            return ResultAnnouncerDto.builder()
                    .message("Incorrect coupon id or the draw has not yet taken place")
                    .build();
        }
    }

    private static String createMessage(int hitNumbers) {

        switch (hitNumbers) {
            case 6 -> {
                return "Congratulations!! You hit the jackpot!!";
            }
            case 1, 2, 3, 4, 5 -> {
                return "Congratulations!! You hit " + hitNumbers + " numbers";
            }
            case 0 -> {
                return "You didn't hit any numbers. Try again";
            }
            default -> throw new IllegalStateException("hit numbers was: " + hitNumbers);
        }
    }
}
