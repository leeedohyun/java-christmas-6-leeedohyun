package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {10_000, 15_000, 20_000})
    public void 총_주문_금액이_10000원_이상이면_true를_반환한다(final int price) {
        // given
        final Money money = new Money(price);

        // when
        final boolean isOverTenThousand = money.isOverTenThousand();

        // then
        Assertions.assertTrue(isOverTenThousand);
    }

    @ParameterizedTest
    @ValueSource(ints = {9_999, 8_000, 7_000})
    public void 총_주문_금액이_10000원_이하이면_false를_반환한다(final int price) {
        // given
        final Money money = new Money(price);

        // when
        final boolean isNotOverTenThousand = money.isOverTenThousand();

        // then
        Assertions.assertFalse(isNotOverTenThousand);
    }
}