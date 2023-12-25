package christmas.model.order;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import christmas.model.Date;
import christmas.model.GiveawayEvent;
import christmas.model.Price;
import christmas.model.discount.DiscountManager;
import christmas.model.menu.Menu;

class OrderTest {

    private Order order;

    @BeforeEach
    void beforeEach() {
        final Date date = new Date(LocalDate.of(2023, 12, 3));
        final OrderDetail detail = new OrderDetail(
                Map.of(Menu.T_BONE_STEAK, 1, Menu.BBQ_RIBS, 1, Menu.CHOCOLATE_CAKE, 2, Menu.ZERO_COLA, 1));
        final Price priceBeforeDiscount = new Price(142_000);
        final DiscountManager discountManager = new DiscountManager();
        final GiveawayEvent giveawayEvent = GiveawayEvent.create(priceBeforeDiscount);
        order = new Order(discountManager, new OrderHistory(date, detail), giveawayEvent);
    }

    @Test
    public void 전체_할인_금액_계산_테스트() {
        // when
        final Price discountPrice = order.calculateTotalDiscountPrice();

        //then
        Assertions.assertEquals(discountPrice, new Price(6_246));
    }

    @Test
    public void 할인된_결제_금액_계산_테스트() {
        // given
        final Price discountPrice = order.calculateTotalDiscountPrice();

        // when
        final Price discountedPrice = order.calculateDiscountedPrice(discountPrice);

        //then
        Assertions.assertEquals(discountedPrice, new Price(135_754));
    }
}
