package pl.lotto.numberreceiver;

import lombok.Builder;

@Builder
class NumberReciverInputError {
    private boolean error = true;
    private String message;

    NumberReciverInputError() {
    }

    NumberReciverInputError(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    NumberReciverInputError(boolean error) {
        this.error = error;
    }

    boolean isError() {
        return error;
    }

    String getMessage() {
        return message;
    }

    void setError(boolean error) {
        this.error = error;
    }

    void setMessage(String message) {
        this.message = message;
    }
}
