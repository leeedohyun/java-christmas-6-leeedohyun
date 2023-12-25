package christmas.model;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 23})
    public void 방문_날짜는_1이상_31이하의_숫자만_입력할_수_있다(final int day) {
        // then
        assertThatNoException().isThrownBy(() -> new Date(day));
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, 0, 32, 40})
    public void 방문_날짜는_1이상_31이하의_숫자가_아닌_경우_예외가_발생한다(final int date) {
        // then
        assertThatIllegalArgumentException().isThrownBy(() -> new Date(date));
    }

    @Test
    public void 주말인_경우_true를_반환한다() {
        // given
        final Date dateOfVisit = new Date(1);

        // when
        final boolean isWeekend = dateOfVisit.isWeekend();

        // then
        Assertions.assertTrue(isWeekend);
    }

    @Test
    public void 평일인_경우_false를_반환한다() {
        // given
        final Date dateOfVisit = new Date(4);

        // when
        final boolean isWeekend = dateOfVisit.isWeekend();

        // then
        Assertions.assertFalse(isWeekend);
    }

    @ParameterizedTest
    @ValueSource(ints = {24, 25})
    public void 크리스마스_이전이거나_당일인_경우_true를_반환한다(final int date) {
        // given
        final Date dateOfVisit = new Date(date);

        // when
        final boolean beforeOrEqualChristmas = dateOfVisit.isBeforeOrEqualChristmas();

        // then
        Assertions.assertTrue(beforeOrEqualChristmas);
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 30})
    public void 크리스마스_이후인_경우_false를_반환한다(final int date) {
        // given
        final Date dateOfVisit = new Date(date);

        // when
        boolean beforeOrEqualChristmas = dateOfVisit.isBeforeOrEqualChristmas();

        // then
        Assertions.assertFalse(beforeOrEqualChristmas);
    }
    
    @Test
    public void 방문_날짜와_이벤트_시작_날짜의_차이_테스트() {
        // given
        final Date dateOfVisit = new Date(6);

        // when
        final int gapUntilEventStart = dateOfVisit.calculateDaysUntilEventStart();

        // then
        Assertions.assertEquals(gapUntilEventStart, 5);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 25, 31})
    public void 별이_있는_날짜는_true를_반환한다(final int date) {
        // given
        final Date dateOfVisit = new Date(date);

        // when
        final boolean hasStar = dateOfVisit.hasStar();

        // then
        Assertions.assertTrue(hasStar);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 13, 29})
    public void 별이_없는_날짜는_false를_반환한다(final int date) {
        // given
        final Date dateOfVisit = new Date(date);

        // when
        final boolean hasNotStar = dateOfVisit.hasStar();

        // then
        Assertions.assertFalse(hasNotStar);
    }
}
