package christmas.model;

import christmas.model.discount.DiscountManager;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;

    @BeforeEach
    void beforeEach() {
        final Day day = new Day(LocalDate.of(2023, 12, 3));
        final OrderDetail detail = new OrderDetail(
                Map.of(Menu.T_BONE_STEAK, 1, Menu.BBQ_RIBS, 1, Menu.CHOCOLATE_CAKE, 2, Menu.ZERO_COLA, 1));
        final Money priceBeforeDiscount = new Money(142_000);
        final DiscountManager discountManager = new DiscountManager();
        final GiveawayEvent giveawayEvent = GiveawayEvent.create(priceBeforeDiscount);
        order = new Order(day, detail, priceBeforeDiscount, discountManager, giveawayEvent);
    }

    @Test
    public void 전체_할인_금액_계산_테스트() {
        // when
        final Money discountPrice = order.calculateTotalDiscountPrice();

        //then
        Assertions.assertEquals(discountPrice, new Money(6_246));
    }

    @Test
    public void 할인된_결제_금액_계산_테스트() {
        // given
        final Money discountPrice = order.calculateTotalDiscountPrice();

        // when
        final Money discountedPrice = order.calculateDiscountedPrice(discountPrice);

        //then
        Assertions.assertEquals(discountedPrice, new Money(135_754));
    }
}