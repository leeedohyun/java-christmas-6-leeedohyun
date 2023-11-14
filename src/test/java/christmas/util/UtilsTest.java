package christmas.util;

import christmas.model.Menu;
import christmas.util.Utils;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "23"})
    public void 방문_날짜는_숫자만_입력할_수_있다(final String day) {
        // then
        Assertions.assertThatNoException()
                .isThrownBy(() -> Utils.convertStringToLocalDate(day));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1e", "10b", "23x"})
    public void 방문_날짜가_숫자가_아닌_경우_예외가_발생한다(final String day) {
        // then
        Assertions.assertThatThrownBy(() -> Utils.convertStringToLocalDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "23"})
    public void 방문_날짜는_1이상_31이하의_숫자만_입력할_수_있다(final String day) {
        // then
        Assertions.assertThatNoException()
                .isThrownBy(() -> Utils.convertStringToLocalDate(day));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-3", "0", "32", "40"})
    public void 방문_날짜는_1이상_31이하의_숫자가_아닌_경우_예외가_발생한다(final String day) {
        // then
        Assertions.assertThatThrownBy(() -> Utils.convertStringToLocalDate(day))
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

    @Test
    public void 주문_내역_Map으로_변환한다() {
        // given
        final List<String> menus = List.of("양송이수프-1", "바비큐립-2", "레드와인-1");

        // when
        final Map<Menu, Integer> orderedMenus = Utils.convertToMenuQuantityMap(menus);

        // then
        Assertions.assertThat(orderedMenus).hasSize(3)
                .containsKeys(Menu.getMenuByName("양송이수프"));
    }

    @Test
    public void 주문_메뉴의_개수가_숫자가_아니면_예외가_발생한다() {
        // given
        final List<String> menus = List.of("양송이수프-1j", "바비큐립-2", "레드와인-1");

        // then
        Assertions.assertThatThrownBy(() -> Utils.convertToMenuQuantityMap(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}