package christmas.model;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    public void 할인된_금액_테스트() {
        // given
        final int totalPrice = 10000;
        final int discountPrice = 1000;

        // when
        final Order order = new Order();
        final int discountedPrice = order.calculateDiscountedPrice(totalPrice, discountPrice);

        // then
        Assertions.assertEquals(discountedPrice, 9_000);
    }

    @Test
    public void 주문_내역에서_메인_메뉴_개수_테스트() {
        // given
        final List<Menu> orderedMenu = List.of(Menu.CHAMPAGNE, Menu.CHRISTMAS_PASTA, Menu.T_BONE_STEAK, Menu.ICE_CREAM);

        // when
        final Order order = new Order();
        final int countedMainMenu = order.countMainMenu(orderedMenu);

        // then
        Assertions.assertEquals(countedMainMenu, 2);
    }

    @Test
    public void 주문_내역에서_디저트_개수_테스트() {
        // given
        final List<Menu> orderedMenu = List.of(Menu.CHAMPAGNE, Menu.CHRISTMAS_PASTA, Menu.T_BONE_STEAK, Menu.ICE_CREAM);

        // when
        final Order order = new Order();
        final int countedMainMenu = order.countDessert(orderedMenu);

        // then
        Assertions.assertEquals(countedMainMenu, 1);
    }

    @Test
    public void 할인전_총_주문_금액_계산하는_기능_테스트() {
        // given
        final List<Menu> orderedMenu = List.of(Menu.CHAMPAGNE, Menu.CHRISTMAS_PASTA, Menu.T_BONE_STEAK, Menu.ICE_CREAM);

        // when
        final Order order = new Order();
        final Money priceBeforeDiscount = order.calculateOrderedPriceBeforeDiscount(orderedMenu);

        // then
        Assertions.assertEquals(priceBeforeDiscount, new Money(110_000));
    }
}