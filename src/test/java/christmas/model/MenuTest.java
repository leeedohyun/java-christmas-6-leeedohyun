package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"타파스", "시저샐러드", "해산물파스타"})
    public void 주문_메뉴가_메뉴판에_있는_경우_정상적으로_동작한다(String menu) {
        Assertions.assertThatNoException()
                .isThrownBy(() -> Menu.validateMenuPresence(menu));
    }

    @ParameterizedTest
    @ValueSource(strings = {"피자", "치킨", "족발"})
    public void 주문_메뉴가_메뉴판에_없는_경우_예외가_발생한다(String menu) {
        Assertions.assertThatThrownBy(() -> Menu.validateMenuPresence(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}