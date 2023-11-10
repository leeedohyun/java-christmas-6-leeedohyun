package christmas.model;

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
}