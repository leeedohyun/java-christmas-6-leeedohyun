package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {10_000, 15_000, 20_000})
    public void 총_주문_금액이_10000원_이상이면_true를_반환한다(final int price) {
        // given
        final Money money = new Money(price);

        // when
        final boolean isOverTenThousand = money.isEqualAndOver(new Money(10_000));

        // then
        Assertions.assertTrue(isOverTenThousand);
    }

    @ParameterizedTest
    @ValueSource(ints = {9_999, 8_000, 7_000})
    public void 총_주문_금액이_10000원_미만이면_true를_반환한다(final int price) {
        // given
        final Money money = new Money(price);

        // when
        final boolean isNotOverTenThousand = money.isUnder(new Money(10_000));

        // then
        Assertions.assertTrue(isNotOverTenThousand);
    }

    @Test
    public void 더하기_테스트() {
        // given
        final Money money1 = new Money(2_000);
        final Money money2 = new Money(4_000);

        // when
        final Money plussedMoney = money1.plus(money2);

        // then
        Assertions.assertEquals(plussedMoney, new Money(6_000));
    }

    @Test
    public void 곱하기_테스트() {
        // given
        final Money money = new Money(2_000);
        final int quantity = 3;

        // when
        final Money plussedMoney = money.multiply(quantity);

        // then
        Assertions.assertEquals(plussedMoney, new Money(6_000));
    }

    @Test
    public void 빼기_테스트() {
        // given
        final Money money1 = new Money(3_000);
        final Money money2 = new Money(1_000);

        // when
        final Money minusedMoney = money1.minus(money2);

        // then
        Assertions.assertEquals(minusedMoney, new Money(2_000));
    }
}