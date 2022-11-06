package pl.lotto.numberreceiver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class NumberInputValidator {
    public static final int MAX_INPUT_NUMBERS_FROM_USER = 6;
    public static final int MIN_VALUE_IN_INPUT_NUMBERS_FROM_USER = 1;
    public static final int MAX_VALUE_IN_INPUT_NUMBERS_FROM_USER = 99;

    List<String> validationErrors = new LinkedList<>();

    public NumberReciverInputError validate(List<Integer> numbersFromUser) {
        if (!doesUserGaveSixNumbers(numbersFromUser.size())) {
            validationErrors.add("not have six numbers");
        }
        if (!doesUserGaveVariousNumbers(numbersFromUser)) {
            validationErrors.add("not have various numbers");
        }
        if (!doesUserGaveNumbersInRange(numbersFromUser)) {
            validationErrors.add("numbers out off range");
        }
        String message = concatenateErrorMessage(validationErrors);
        if (!validationErrors.isEmpty()) {
            return NumberReciverInputError.builder()
                    .message(message)
                    .error(true)
                    .build();
        }
        return NumberReciverInputError.builder().build();
    }

    private boolean doesUserGaveSixNumbers(int size) {
        return size == MAX_INPUT_NUMBERS_FROM_USER;
    }

    private boolean doesUserGaveVariousNumbers(List<Integer> numbers) {
        return numbers.stream()
                .allMatch(new HashSet<>()::add);
    }

    private boolean doesUserGaveNumbersInRange(List<Integer> numbers) {
        return numbers.stream()
                .noneMatch(this::isNumberOutOfRange);
    }

    private boolean isNumberOutOfRange(int n) {
        return n < MIN_VALUE_IN_INPUT_NUMBERS_FROM_USER || n > MAX_VALUE_IN_INPUT_NUMBERS_FROM_USER;
    }

    private String concatenateErrorMessage(List<String> messages) {
        return messages.stream()
                .collect(Collectors.joining(", ", "Input numbers error: ", ""));
    }
}
