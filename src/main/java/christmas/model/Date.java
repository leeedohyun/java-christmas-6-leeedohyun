package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Date {

    private static final List<Integer> DATES_WITH_STAR = List.of(3, 10, 17, 24, 25, 31);
    private static final LocalDate EVENT_START_DATE = LocalDate.of(Constants.YEAR, Constants.MONTH, 1);
    private static final LocalDate CHRISTMAS = LocalDate.of(Constants.YEAR, Constants.MONTH, 25);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(Constants.YEAR, Constants.MONTH, 31);

    private final LocalDate day;

    public Date(final LocalDate day) {
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
        return DATES_WITH_STAR.contains(day.getDayOfMonth());
    }

    public int getMonth() {
        return day.getMonthValue();
    }

    public int getDay() {
        return day.getDayOfMonth();
    }
}
