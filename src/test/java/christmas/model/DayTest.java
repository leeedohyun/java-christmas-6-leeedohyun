package christmas.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"2023-12-24", "2023-12-25"})
    public void 크리스마스_이전이거나_당일인_경우_true를_반환한다(String date) {
        // given
        LocalDate localDate = LocalDate.parse(date);
        Day visitedDay = new Day(localDate);

        // when
        boolean beforeOrEqualChristmas = visitedDay.isBeforeOrEqualChristmas();

        // then
        Assertions.assertEquals(beforeOrEqualChristmas, true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-12-26", "2023-12-30"})
    public void 크리스마스_이후인_경우_false를_반환한다(String date) {
        // given
        LocalDate localDate = LocalDate.parse(date);
        Day visitedDay = new Day(localDate);

        // when
        boolean beforeOrEqualChristmas = visitedDay.isBeforeOrEqualChristmas();

        // then
        Assertions.assertEquals(beforeOrEqualChristmas, false);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-12-04", "2023-12-31"})
    public void 이벤트_이전인_경우_true를_반환한다(String date) {
        // given
        LocalDate localDate = LocalDate.parse(date);
        Day visitedDay = new Day(localDate);

        // when
        boolean beforeOrEqualChristmas = visitedDay.isEventNotEnded();

        // then
        Assertions.assertEquals(beforeOrEqualChristmas, true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2024-01-01", "2024-01-05"})
    public void 이벤트_이후인_경우_false를_반환한다(String date) {
        // given
        LocalDate localDate = LocalDate.parse(date);
        Day visitedDay = new Day(localDate);

        // when
        boolean beforeOrEqualChristmas = visitedDay.isEventNotEnded();

        // then
        Assertions.assertEquals(beforeOrEqualChristmas, false);
    }
    
    @Test
    public void 방문_날짜와_이벤트_시작_날짜의_차이_테스트() {
        // given
        Day visitedDay = new Day(LocalDate.of(2023, 12, 6));

        // when
        int gapUntilEventStart = visitedDay.calculateDaysUntilEventStart();

        // then
        Assertions.assertEquals(gapUntilEventStart, 5);
    }
}