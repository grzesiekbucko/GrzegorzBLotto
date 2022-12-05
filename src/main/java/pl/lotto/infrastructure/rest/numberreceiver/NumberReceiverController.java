package pl.lotto.infrastructure.rest.numberreceiver;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

@RestController
@AllArgsConstructor
public class NumberReceiverController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/inputNumbers")
    public ResponseEntity<NumberReceiverResultDto> postInputNumbers(@RequestBody InputNumbersRequest inputNumbersRequest) {
        List<Integer> numbers = inputNumbersRequest.getNumbers();
        NumberReceiverResultDto numberReceiverResultDto = numberReceiverFacade.inputNumbers(numbers);
        if (numberReceiverResultDto.error()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(numberReceiverResultDto);
        }
        return ResponseEntity.ok(numberReceiverResultDto);
    }
}
