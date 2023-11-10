package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Day {

    private static final LocalDate EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 31);

    private final LocalDate day;

    public Day(final LocalDate day) {
        this.day = day;
    }

    public boolean isWeekend() {
        return day.getDayOfWeek() == DayOfWeek.FRIDAY || day.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isBeforeOrEqualChristmas() {
        return !day.isAfter(CHRISTMAS);
    }

    public boolean isEventNotEnded() {
        return !day.isAfter(EVENT_END_DATE);
    }

    public int calculateDaysUntilEventStart() {
        return (int) ChronoUnit.DAYS.between(EVENT_START_DATE, day);
    }
}
