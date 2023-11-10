package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Day {

    private static final int YEAR = 2023;
    private static final int Month = 12;
    private static final int START_DATE = 1;
    private static final int CHRISTMAS_DATE = 25;
    private static final int END_DATE = 31;

    private static final LocalDate EVENT_START_DATE = LocalDate.of(YEAR, Month, START_DATE);
    private static final LocalDate CHRISTMAS = LocalDate.of(YEAR, Month, CHRISTMAS_DATE);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(YEAR, Month, END_DATE);

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
