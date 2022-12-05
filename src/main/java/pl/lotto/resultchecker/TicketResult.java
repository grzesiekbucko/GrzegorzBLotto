package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResult implements Serializable {

    @Id
    String tickedId;
    LocalDateTime lotteryDate;
    List<Integer> winningNumbers;
    List<Integer> userNumbers;
    List<Integer> hitNumbers;

}
