package christmas.model.discount;

import christmas.model.Day;
import christmas.model.Menu;
import christmas.model.Money;
import christmas.model.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountManagerTest {

    @Test
    public void 할인_금액_리스트를_반환합니다() {
        // given
        Day day = new Day(LocalDate.of(2023, 12, 3));
        Money money = new Money(142_000);
        Order order = new Order(
                Map.of(Menu.T_BONE_STEAK, 1, Menu.BBQ_RIBS, 1, Menu.CHOCOLATE_CAKE, 2, Menu.ZERO_COLA, 1));

        // when
        DiscountManager discountManager = new DiscountManager();
        List<Money> monies = discountManager.calculateTotalDiscountPrice(day, money, order);

        // then
        Assertions.assertThat(monies)
                .contains(new Money(1_200), new Money(4_046));
    }
}