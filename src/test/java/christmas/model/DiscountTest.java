package christmas.model;

import christmas.model.discount.DDay;
import christmas.model.discount.Discount;
import christmas.model.discount.Special;
import christmas.model.discount.WeekDay;
import christmas.model.discount.Weekend;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountTest {

    @Test
    public void 디데이_할인_금액_계산_적용되는_경우_테스트() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 10));
        final Money totalPrice = new Money(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount dDay = new DDay();
        final Money discountPrice = dDay.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(1900));
    }

    @Test
    public void 날짜가_지난_경우_디데이_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 26));
        final Money totalPrice = new Money(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount dDay = new DDay();
        final Money discountPrice = dDay.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(0));
    }

    @Test
    public void 주문_금액이_10000원이_넘지_않은_경우_디데이_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 10));
        final Money totalPrice = new Money(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount dDay = new DDay();
        final Money discountPrice = dDay.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(0));
    }

    @Test
    public void 평일_할인_테스트() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 10));
        final Money totalPrice = new Money(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.CHOCOLATE_CAKE, 1, Menu.ICE_CREAM, 2));

        // when
        final Discount weekDay = new WeekDay();
        final Money discountPrice = weekDay.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(6069));
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않는_경우_평일_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 10));
        final Money totalPrice = new Money(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.CHOCOLATE_CAKE, 1, Menu.ICE_CREAM, 2));

        // when
        final Discount weekDay = new WeekDay();
        final Money discountPrice = weekDay.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(0));
    }

    @Test
    public void 디저트_메뉴의_개수가_0개이면_평일_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 10));
        final Money totalPrice = new Money(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount weekDay = new WeekDay();
        final Money discountPrice = weekDay.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(0));
    }

    @Test
    public void 주말_할인_금액_테스트() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 30));
        final Money totalPrice = new Money(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));


        // when
        final Discount weekend = new Weekend();
        final Money discountPrice = weekend.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(4046));
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않는_경우_주말_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 30));
        final Money totalPrice = new Money(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount weekend = new Weekend();
        final Money discountPrice = weekend.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(0));
    }

    @Test
    public void 메인_메뉴의_개수가_0개이면_주말_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 30));
        final Money totalPrice = new Money(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.ICE_CREAM, 2, Menu.CAESAR_SALAD, 4));

        // when
        final Discount weekend = new Weekend();
        final Money discountPrice = weekend.discount(visitedDay, totalPrice, orderDetail);
        Assertions.assertEquals(discountPrice, new Money(0));
    }

    @Test
    public void 특별_할인_테스트() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 3));
        final Money totalPrice = new Money(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount special = new Special();
        final Money discountPrice = special.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(1000));
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않은_경우_특별_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 31));
        final Money totalPrice = new Money(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount special = new Special();
        final Money discountPrice = special.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(0));
    }

    @Test
    public void 별을_가지고_있지_않은_경우_특별_할인_금액은_0원이다() {
        // given
        final Day visitedDay = new Day(LocalDate.of(2023, 12, 30));
        final Money totalPrice = new Money(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount special = new Special();
        final Money discountPrice = special.discount(visitedDay, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Money(0));
    }
}