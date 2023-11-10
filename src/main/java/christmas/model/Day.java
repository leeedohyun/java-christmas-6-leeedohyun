package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Day {

    private final LocalDate day;

    public Day(LocalDate day) {
        this.day = day;
    }

    public boolean isWeekend() {
        return day.getDayOfWeek() == DayOfWeek.FRIDAY || day.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}
