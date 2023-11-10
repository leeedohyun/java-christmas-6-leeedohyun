package christmas.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayTest {

    @Test
    public void 주말인_경우_true를_반환한다() {
        // given
        LocalDate day = LocalDate.of(2023, 12, 1);
        Day visitedDay = new Day(day);

        // when
        boolean isWeekend = visitedDay.isWeekend();

        // then
        Assertions.assertEquals(isWeekend, true);
    }

    @Test
    public void 평일인_경우_false를_반환한다() {
        // given
        LocalDate day = LocalDate.of(2023, 12, 4);
        Day visitedDay = new Day(day);

        // when
        boolean isWeekend = visitedDay.isWeekend();

        // then
        Assertions.assertEquals(isWeekend, false);
    }
}