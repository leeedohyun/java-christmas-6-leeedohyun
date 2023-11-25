package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Date {

    private static final LocalDate EVENT_START_DATE = LocalDate.of(Constants.YEAR, Constants.MONTH, 1);
    private static final LocalDate CHRISTMAS = LocalDate.of(Constants.YEAR, Constants.MONTH, 25);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(Constants.YEAR, Constants.MONTH, 31);

    private final LocalDate date;

    public Date(final LocalDate date) {
        this.date = date;
    }

    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isBeforeOrEqualChristmas() {
        return !date.isAfter(CHRISTMAS);
    }

    public boolean isEventNotEnded() {
        return !date.isAfter(EVENT_END_DATE);
    }

    public int calculateDaysUntilEventStart() {
        return (int) ChronoUnit.DAYS.between(EVENT_START_DATE, date);
    }

    public boolean hasStar() {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.equals(CHRISTMAS);
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public int getDate() {
        return date.getDayOfMonth();
    }
}
