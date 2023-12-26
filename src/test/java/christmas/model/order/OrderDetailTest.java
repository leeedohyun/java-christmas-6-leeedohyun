package christmas.model.order;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import christmas.exception.MenuNotFoundException;
import christmas.model.Price;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;

class OrderDetailTest {

    @Test
    public void 주문_내역에서_메인_메뉴_개수_테스트() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 1,
                Menu.CHRISTMAS_PASTA, 2, Menu.T_BONE_STEAK, 3, Menu.ICE_CREAM, 1);

        // when
        final OrderDetail orderDetail = new OrderDetail(orderedMenu);
        final int countedMainMenu = orderDetail.countNumberOfMenusByMenuType(MenuType.MAIN);

        // then
        Assertions.assertEquals(countedMainMenu, 5);
    }

    @Test
    public void 주문_내역에서_디저트_개수_테스트() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 1,
                Menu.CHRISTMAS_PASTA, 2, Menu.T_BONE_STEAK, 3, Menu.ICE_CREAM, 2);

        // when
        final OrderDetail orderDetail = new OrderDetail(orderedMenu);
        final int countedMainMenu = orderDetail.countNumberOfMenusByMenuType(MenuType.DESSERT);

        // then
        Assertions.assertEquals(countedMainMenu, 2);
    }

    @Test
    public void 할인전_총_주문_금액_계산하는_기능_테스트() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 1,
                Menu.CHRISTMAS_PASTA, 2, Menu.T_BONE_STEAK, 3, Menu.ICE_CREAM, 1);

        // when
        final OrderDetail orderDetail = new OrderDetail(orderedMenu);
        final Price priceBeforeDiscount = orderDetail.calculateOrderedPriceBeforeDiscount();

        // then
        Assertions.assertEquals(priceBeforeDiscount, new Price(245_000));
    }

    @Test
    public void 음료만_주문한_경우_예외가_발생한다() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 3, Menu.RED_WINE, 2);

        // then
        Assertions.assertThrows(MenuNotFoundException.class, () -> new OrderDetail(orderedMenu));
    }

    @Test
    public void 총_주문_개수가_20이_넘으면_예외가_발생한다() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, 5,
                Menu.CHRISTMAS_PASTA, 4, Menu.T_BONE_STEAK, 5, Menu.ICE_CREAM, 7);

        // then
        Assertions.assertThrows(MenuNotFoundException.class, () -> new OrderDetail(orderedMenu));
    }

    @Test
    public void 주문_개수가_1개미만인_메뉴가_있으면_예외가_발생한다() {
        // given
        final Map<Menu, Integer> orderedMenu = Map.of(Menu.CHAMPAGNE, -1,
                Menu.CHRISTMAS_PASTA, 4, Menu.T_BONE_STEAK, 5, Menu.ICE_CREAM, 7);

        // then
        Assertions.assertThrows(MenuNotFoundException.class, () -> new OrderDetail(orderedMenu));
    }
}
