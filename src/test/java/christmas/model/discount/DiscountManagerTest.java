package christmas.model.discount;

import christmas.model.Date;
import christmas.model.menu.Menu;
import christmas.model.Price;
import christmas.model.order.OrderDetail;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountManagerTest {

    @Test
    public void 할인_금액_리스트를_반환합니다() {
        // given
        final Date date = new Date(LocalDate.of(2023, 12, 3));
        final Price price = new Price(142_000);
        OrderDetail orderDetail = new OrderDetail(
                Map.of(Menu.T_BONE_STEAK, 1, Menu.BBQ_RIBS, 1, Menu.CHOCOLATE_CAKE, 2, Menu.ZERO_COLA, 1));

        // when
        final DiscountManager discountManager = new DiscountManager();
        final List<Price> monies = discountManager.calculateDiscountPrices(date, price, orderDetail);

        // then
        Assertions.assertThat(monies)
                .contains(new Price(1_200), new Price(4_046));
    }
}