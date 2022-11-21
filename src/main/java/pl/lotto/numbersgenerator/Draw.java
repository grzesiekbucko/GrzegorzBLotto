package pl.lotto.numbersgenerator;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

class Draw {
    private static final int RANGE_MAX = 99;
    private static final int AMOUNT_OF_NUMBERS = 6;
    private final Clock clock;
    public Draw(Clock clock) {
        this.clock = clock;
    }

    WinningNumbers startDrew() {
        return new WinningNumbers(LocalDateTime.now(clock), generateNumbers());
    }

    private List<Integer> generateNumbers() {
        Random rng = new Random();
        Set<Integer> generated = new HashSet<>();
        while (generated.size() < AMOUNT_OF_NUMBERS) {
            Integer next = rng.nextInt(RANGE_MAX) + 1;
            generated.add(next);
        }
        return generated.stream()
                .sorted()
                .toList();
    }

}
