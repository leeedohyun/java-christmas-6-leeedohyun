package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Day {

    private static final int START_DATE = 1;
    private static final int CHRISTMAS_DATE = 25;
    private static final int END_DATE = 31;
    private static final List<Integer> DAYS_WITH_STAR = List.of(3, 10, 17, 24, 25, 31);
    private static final LocalDate EVENT_START_DATE = LocalDate.of(Constants.YEAR, Constants.MONTH, START_DATE);
    private static final LocalDate CHRISTMAS = LocalDate.of(Constants.YEAR, Constants.MONTH, CHRISTMAS_DATE);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(Constants.YEAR, Constants.MONTH, END_DATE);

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

    public boolean hasStar() {
        return DAYS_WITH_STAR.contains(day.getDayOfMonth());
    }

    public int getMonth() {
        return day.getMonthValue();
    }

    public int getDay() {
        return day.getDayOfMonth();
    }
}
