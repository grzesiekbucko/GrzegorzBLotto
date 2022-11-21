package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

class DrawDatesFinder {

    public static final DayOfWeek DRAW_DAY_OF_WEEK = DayOfWeek.SATURDAY;
    public static final int DRAW_HOUR = 12;

    Clock clock;

    public DrawDatesFinder(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime upcomingDrawDate() {
        LocalDateTime ticketDate = LocalDateTime.now(clock);
        if (ticketDate.getHour() < DRAW_HOUR) {
            return ticketDate.with(TemporalAdjusters.nextOrSame(DRAW_DAY_OF_WEEK)).withHour(DRAW_HOUR).withMinute(0).withSecond(0).withNano(0);
        } else {
            return ticketDate.with(TemporalAdjusters.next(DRAW_DAY_OF_WEEK)).withHour(DRAW_HOUR).withMinute(0).withSecond(0).withNano(0);
        }
    }
}
