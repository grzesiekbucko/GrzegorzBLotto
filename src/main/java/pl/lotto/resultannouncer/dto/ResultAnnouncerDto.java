package pl.lotto.resultannouncer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class ResultAnnouncerDto {

    UUID tickedId;
    LocalDateTime lotteryDate;
    List<Integer> winningNumbers;
    List<Integer> userNumbers;
    List<Integer> hitNumbers;
    String message;

}
