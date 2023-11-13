package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 2_000, 3_000, 4_990})
    public void 혜택_금액이_5000원_미만인_경우_배지는_없다(final int price) {
        // given
        final Price money = new Price(price);

        // when
        final Badge none = Badge.decide(money);

        // then
        Assertions.assertEquals(none, Badge.NONE);
    }

    @ParameterizedTest
    @ValueSource(ints = {5_000, 5_500, 6_000, 9_900})
    public void 혜택_금액이_5000원_이상_10000원_미만인_경우_별_배지이다(final int price) {
        // given
        final Price money = new Price(price);

        // when
        final Badge star = Badge.decide(money);

        // then
        Assertions.assertEquals(star, Badge.STAR);
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000, 15_000, 16_000, 19_900})
    public void 혜택_금액이_10000원_이상_20000원_미만인_경우_트리_배지이다(final int price) {
        // given
        final Price money = new Price(price);

        // when
        final Badge tree = Badge.decide(money);

        // then
        Assertions.assertEquals(tree, Badge.TREE);
    }

    @ParameterizedTest
    @ValueSource(ints = {20_000, 25_000, 26_000, 100_000})
    public void 혜택_금액이_20000원_이상인_경우_산타_배지이다(final int price) {
        // given
        final Price money = new Price(price);

        // when
        final Badge santa = Badge.decide(money);

        // then
        Assertions.assertEquals(santa, Badge.SANTA);
    }
}