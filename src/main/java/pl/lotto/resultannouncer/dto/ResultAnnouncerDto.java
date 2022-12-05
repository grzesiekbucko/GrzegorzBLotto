package pl.lotto.resultannouncer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ResultAnnouncerDto {

    String tickedId;
    LocalDateTime lotteryDate;
    List<Integer> winningNumbers;
    List<Integer> userNumbers;
    List<Integer> hitNumbers;
    String message;

}
