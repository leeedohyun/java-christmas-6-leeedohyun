package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Date {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int FIRST_DATE = 1;
    private static final int END_DATE = 31;

    private static final LocalDate EVENT_START_DATE = LocalDate.of(YEAR, MONTH, FIRST_DATE);
    private static final LocalDate CHRISTMAS = LocalDate.of(YEAR, MONTH, 25);
    private static final String DATE_FORMAT_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final LocalDate date;

    public Date(final int date) {
        validateInRange(date);
        this.date = LocalDate.of(YEAR, MONTH, date);
    }

    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isBeforeOrEqualChristmas() {
        return !date.isAfter(CHRISTMAS);
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

    private void validateInRange(final int date) {
        if (date < FIRST_DATE || date > END_DATE) {
            throw new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
