package christmas.model;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    public void 할인된_금액_테스트() {
        // given
        final int totalPrice = 10_000;
        final int discountPrice = 1_000;

        // when
        final int discountedPrice = Order.calculateDiscountedPrice(totalPrice, discountPrice);

        // then
        Assertions.assertEquals(discountedPrice, 9_000);
    }

    @Test
    public void 주문_내역에서_메인_메뉴_개수_테스트() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 1,
                Menu.CHRISTMAS_PASTA, 2, Menu.T_BONE_STEAK, 3, Menu.ICE_CREAM, 1);

        // when
        final Order order = new Order(orderedMenu);
        final int countedMainMenu = order.countMainMenu();

        // then
        Assertions.assertEquals(countedMainMenu, 2);
    }

    @Test
    public void 주문_내역에서_디저트_개수_테스트() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 1,
                Menu.CHRISTMAS_PASTA, 2, Menu.T_BONE_STEAK, 3, Menu.ICE_CREAM, 1);

        // when
        final Order order = new Order(orderedMenu);
        final int countedMainMenu = order.countDessert();

        // then
        Assertions.assertEquals(countedMainMenu, 1);
    }

    @Test
    public void 할인전_총_주문_금액_계산하는_기능_테스트() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 1,
                Menu.CHRISTMAS_PASTA, 2, Menu.T_BONE_STEAK, 3, Menu.ICE_CREAM, 1);

        // when
        final Order order = new Order(orderedMenu);
        final Money priceBeforeDiscount = order.calculateOrderedPriceBeforeDiscount();

        // then
        Assertions.assertEquals(priceBeforeDiscount, new Money(110_000));
    }

    @Test
    public void 음료만_주문한_경우_예외가_발생한다() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 3, Menu.RED_WINE, 2);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Order(orderedMenu));
    }

    @Test
    public void 총_주문_개수가_20이_넘으면_예외가_발생한다() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 5,
                Menu.CHRISTMAS_PASTA, 4, Menu.T_BONE_STEAK, 5, Menu.ICE_CREAM, 7);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Order(orderedMenu));
    }

    @Test
    public void 주문_개수가_1개미만인_메뉴가_있으면_예외가_발생한다() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, -1,
                Menu.CHRISTMAS_PASTA, 4, Menu.T_BONE_STEAK, 5, Menu.ICE_CREAM, 7);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Order(orderedMenu));
    }
}