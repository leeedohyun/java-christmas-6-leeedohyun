package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Day {

    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 31);

    private final LocalDate day;

    public Day(LocalDate day) {
        this.day = day;
    }

    public boolean isWeekend() {
        return day.getDayOfWeek() == DayOfWeek.FRIDAY || day.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isBeforeOrEqualChristmas() {
        return day.isBefore(CHRISTMAS) || day.isEqual(CHRISTMAS);
    }

    public boolean isEventNotEnded() {
        return day.isBefore(EVENT_END_DATE) || day.isEqual(EVENT_END_DATE);
    }
}
