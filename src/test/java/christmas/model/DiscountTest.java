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
}