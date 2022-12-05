package pl.lotto.infrastructure.rest.numberreceiver;

import java.util.List;

public class InputNumbersRequest {

    List<Integer> numbers;

    InputNumbersRequest() {
    }

    List<Integer> getNumbers() {
        return numbers;
    }

    void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
