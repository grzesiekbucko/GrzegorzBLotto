package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TicketResult {

    UUID tickedId;
    LocalDateTime lotteryDate;
    List<Integer> winningNumbers;
    List<Integer> userNumbers;
    List<Integer> hitNumbers;

    public TicketResult(UUID tickedId, LocalDateTime lotteryDate, List<Integer> winningNumbers, List<Integer> userNumbers, List<Integer> hitNumbers) {
        this.tickedId = tickedId;
        this.lotteryDate = lotteryDate;
        this.winningNumbers = winningNumbers;
        this.userNumbers = userNumbers;
        this.hitNumbers = hitNumbers;
    }

    public UUID getTickedId() {
        return tickedId;
    }

    public void setTickedId(UUID tickedId) {
        this.tickedId = tickedId;
    }

    public LocalDateTime getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(LocalDateTime lotteryDate) {
        this.lotteryDate = lotteryDate;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public List<Integer> getUserNumbers() {
        return userNumbers;
    }

    public void setUserNumbers(List<Integer> userNumbers) {
        this.userNumbers = userNumbers;
    }

    public List<Integer> getHitNumbers() {
        return hitNumbers;
    }

    public void setHitNumbers(List<Integer> hitNumbers) {
        this.hitNumbers = hitNumbers;
    }
}
