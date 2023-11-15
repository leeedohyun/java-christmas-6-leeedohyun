package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PriceTest {

    @ParameterizedTest
    @ValueSource(ints = {10_000, 15_000, 20_000})
    public void 총_주문_금액이_10000원_이상이면_true를_반환한다(final int price) {
        // given
        final Price money = new Price(price);

        // when
        final boolean isOverTenThousand = money.isEqualAndOver(new Price(10_000));

        // then
        Assertions.assertTrue(isOverTenThousand);
    }

    @ParameterizedTest
    @ValueSource(ints = {9_999, 8_000, 7_000})
    public void 총_주문_금액이_10000원_미만이면_true를_반환한다(final int price) {
        // given
        final Price money = new Price(price);

        // when
        final boolean isNotOverTenThousand = money.isUnder(new Price(10_000));

        // then
        Assertions.assertTrue(isNotOverTenThousand);
    }

    @Test
    public void 더하기_테스트() {
        // given
        final Price price1 = new Price(2_000);
        final Price price2 = new Price(4_000);

        // when
        final Price plussedPrice = price1.plus(price2);

        // then
        Assertions.assertEquals(plussedPrice, new Price(6_000));
    }

    @Test
    public void 곱하기_테스트() {
        // given
        final Price price = new Price(2_000);
        final int quantity = 3;

        // when
        final Price plussedPrice = price.multiply(quantity);

        // then
        Assertions.assertEquals(plussedPrice, new Price(6_000));
    }

    @Test
    public void 빼기_테스트() {
        // given
        final Price price1 = new Price(3_000);
        final Price price2 = new Price(1_000);

        // when
        final Price minusedPrice = price1.minus(price2);

        // then
        Assertions.assertEquals(minusedPrice, new Price(2_000));
    }

    @Test
    public void 빼기의_결과가_음수인_경우_예외가_발생한다() {
        // given
        final Price price1 = new Price(500);
        final Price price2 = new Price(1_000);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> price1.minus(price2));
    }
}