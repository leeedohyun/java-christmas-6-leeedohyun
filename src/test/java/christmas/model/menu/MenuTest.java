package christmas.model.menu;

import christmas.model.Price;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    private Menu menu;

    @BeforeEach
    public void beforeEach() {
        menu = Menu.BBQ_RIBS;
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스", "시저샐러드", "해산물파스타"})
    public void 주문_메뉴가_메뉴판에_있는_경우_정상적으로_동작한다(final String menu) {
        Assertions.assertThatNoException()
                .isThrownBy(() -> Menu.getMenuByName(menu));
    }

    @ParameterizedTest
    @ValueSource(strings = {"피자", "치킨", "족발"})
    public void 주문_메뉴가_메뉴판에_없는_경우_예외가_발생한다(final String menu) {
        Assertions.assertThatThrownBy(() -> Menu.getMenuByName(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 주문_메뉴가_매뉴판에_있는_경우_Menu를_반환한다() {
        // given
        final String menu = "제로콜라";

        // when
        final Menu zeroCola = Menu.getMenuByName(menu);

        // then
        Assertions.assertThat(zeroCola).isEqualTo(Menu.ZERO_COLA);
    }

    @Test
    void 메뉴의_가격을_반환하는_기능_테스트() {
        Assertions.assertThat(Menu.getPriceByMenu(Menu.T_BONE_STEAK)).isEqualTo(new Price(55_000));
    }

    @Test
    void 메뉴_수량을_입력하면_하나의_메뉴에_대한_가격을_계산한다() {
        // given
        final int quantity = 3;

        // when
        final Price price = menu.calculateMenuPrice(quantity);

        // then
        Assertions.assertThat(price).isEqualTo(new Price(162_000));
    }
}