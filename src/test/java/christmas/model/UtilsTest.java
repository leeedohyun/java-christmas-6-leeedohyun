package christmas.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
    public void 콤마를_기준으로_서로_다른_메뉴를_분리한다() {
        // given
        final String input = "양송이수프-1,바비큐립-2,레드와인-1";

        // when
        final List<String> menus = Utils.splitDifferentMenus(input);

        // then
        Assertions.assertThat(menus).hasSize(3)
                .contains("양송이수프-1", "바비큐립-2", "레드와인-1");
    }
}