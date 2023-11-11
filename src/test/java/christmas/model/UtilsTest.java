package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "23"})
    public void 방문_날짜는_숫자만_입력할_수_있다(String day) {
        // then
        Assertions.assertThatNoException()
                .isThrownBy(() -> Utils.convertStringToInt(day));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1e", "10b", "23x"})
    public void 방문_날짜가_숫자가_아닌_경우_예외가_발생한다(String day) {
        // then
        Assertions.assertThatThrownBy(() -> Utils.convertStringToInt(day))
                .isInstanceOf(IllegalArgumentException.class);
    }
}