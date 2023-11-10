package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountTest {

    @Test
    public void 디데이_할인_금액_계산_적용되는_경우_테스트() {
        // given
        int visitedDay = 10;
        int totalPrice = 10000;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountDDay(visitedDay, totalPrice);

        // then
        Assertions.assertEquals(discountPrice, 1900);
    }

    @Test
    public void 날짜가_지난_경우_디데이_할인_금액은_0원이다() {
        // given
        int visitedDay = 26;
        int totalPrice = 10000;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountDDay(visitedDay, totalPrice);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }

    @Test
    public void 주문_금액이_10000원이_넘지_않은_경우_디데이_할인_금액은_0원이다() {
        // given
        int visitedDay = 10;
        int totalPrice = 9900;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountDDay(visitedDay, totalPrice);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }

    @Test
    public void 평일_할인_테스트() {
        // given
        int visitedDay = 10;
        int totalPrice = 10000;
        int numberOfDessertMenu = 3;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountWeekDay(visitedDay, totalPrice, numberOfDessertMenu);

        // then
        Assertions.assertEquals(discountPrice, 6069);
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않는_경우_평일_할인_금액은_0원이다() {
        // given
        int visitedDay = 10;
        int totalPrice = 9900;
        int numberOfDessertMenu = 3;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountWeekDay(visitedDay, totalPrice, numberOfDessertMenu);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }

    @Test
    public void 디저트_메뉴의_개수가_0개이면_평일_할인_금액은_0원이다() {
        // given
        int visitedDay = 10;
        int totalPrice = 9900;
        int numberOfDessertMenu = 0;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountWeekDay(visitedDay, totalPrice, numberOfDessertMenu);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }

    @Test
    public void 주말_할인_금액_테스트() {
        // given
        int visitedDay = 31;
        int totalPrice = 10000;
        int numberOfMainMenu = 2;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountWeekend(visitedDay, totalPrice, numberOfMainMenu);

        // then
        Assertions.assertEquals(discountPrice, 4046);
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않는_경우_주말_할인_금액은_0원이다() {
        // given
        int visitedDay = 31;
        int totalPrice = 9900;
        int numberOfMainMenu = 2;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountWeekend(visitedDay, totalPrice, numberOfMainMenu);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }

    @Test
    public void 메인_메뉴의_개수가_0개이면_평일_할인_금액은_0원이다() {
        // given
        int visitedDay = 31;
        int totalPrice = 9900;
        int numberOfMainMenu = 0;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountWeekDay(visitedDay, totalPrice, numberOfMainMenu);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }

    @Test
    public void 특별_할인_테스트() {
        // given
        int visitedDay = 3;
        int totalPrice = 10000;
        boolean hasStar = true;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountSpecial(visitedDay, totalPrice, hasStar);

        // then
        Assertions.assertEquals(discountPrice, 1000);
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않은_경우_특별_할인_금액은_0원이다() {
        // given
        int visitedDay = 3;
        int totalPrice = 9900;
        boolean hasStar = true;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountSpecial(visitedDay, totalPrice, hasStar);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }

    @Test
    public void 별을_가지고_있지_않은_경우_특별_할인_금액은_0원이다() {
        // given
        int visitedDay = 3;
        int totalPrice = 10000;
        boolean hasStar = false;

        Discount discount = new Discount();

        // when
        int discountPrice = discount.discountSpecial(visitedDay, totalPrice, hasStar);

        // then
        Assertions.assertEquals(discountPrice, 0);
    }
}