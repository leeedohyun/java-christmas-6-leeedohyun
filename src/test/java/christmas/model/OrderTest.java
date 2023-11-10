package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    public void 할인된_금액_테스트() {
        // given
        int totalPrice = 10_000;
        int discountPrice = 1_000;

        Order order = new Order();

        // when
        int discountedPrice = order.calculateDiscountedPrice(totalPrice, discountPrice);

        // then
        Assertions.assertEquals(discountedPrice, 9_000);
    }
}