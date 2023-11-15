package christmas.model;

import christmas.model.discount.DDay;
import christmas.model.discount.Discount;
import christmas.model.discount.Special;
import christmas.model.discount.WeekDay;
import christmas.model.discount.Weekend;
import christmas.model.order.OrderDetail;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountTest {

    @Test
    public void 디데이_할인_금액_계산_적용되는_경우_테스트() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 10));
        final Price totalPrice = new Price(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount dDay = new DDay();
        final Price discountPrice = dDay.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(1900));
    }

    @Test
    public void 날짜가_지난_경우_디데이_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 26));
        final Price totalPrice = new Price(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount dDay = new DDay();
        final Price discountPrice = dDay.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(0));
    }

    @Test
    public void 주문_금액이_10000원이_넘지_않은_경우_디데이_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 10));
        final Price totalPrice = new Price(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount dDay = new DDay();
        final Price discountPrice = dDay.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(0));
    }

    @Test
    public void 평일_할인_테스트() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 10));
        final Price totalPrice = new Price(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.CHOCOLATE_CAKE, 1, Menu.ICE_CREAM, 2));

        // when
        final Discount weekDay = new WeekDay();
        final Price discountPrice = weekDay.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(6069));
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않는_경우_평일_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 10));
        final Price totalPrice = new Price(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.CHOCOLATE_CAKE, 1, Menu.ICE_CREAM, 2));

        // when
        final Discount weekDay = new WeekDay();
        final Price discountPrice = weekDay.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(0));
    }

    @Test
    public void 디저트_메뉴의_개수가_0개이면_평일_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 10));
        final Price totalPrice = new Price(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1));

        // when
        final Discount weekDay = new WeekDay();
        final Price discountPrice = weekDay.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(0));
    }

    @Test
    public void 주말_할인_금액_테스트() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 30));
        final Price totalPrice = new Price(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));


        // when
        final Discount weekend = new Weekend();
        final Price discountPrice = weekend.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(4046));
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않는_경우_주말_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 30));
        final Price totalPrice = new Price(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount weekend = new Weekend();
        final Price discountPrice = weekend.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(0));
    }

    @Test
    public void 메인_메뉴의_개수가_0개이면_주말_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 30));
        final Price totalPrice = new Price(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.ICE_CREAM, 2, Menu.CAESAR_SALAD, 4));

        // when
        final Discount weekend = new Weekend();
        final Price discountPrice = weekend.discount(visitedDate, totalPrice, orderDetail);
        Assertions.assertEquals(discountPrice, new Price(0));
    }

    @Test
    public void 특별_할인_테스트() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 3));
        final Price totalPrice = new Price(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount special = new Special();
        final Price discountPrice = special.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(1000));
    }

    @Test
    public void 주문_금액이_10000원을_넘지_않은_경우_특별_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 31));
        final Price totalPrice = new Price(9900);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount special = new Special();
        final Price discountPrice = special.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(0));
    }

    @Test
    public void 별을_가지고_있지_않은_경우_특별_할인_금액은_0원이다() {
        // given
        final Date visitedDate = new Date(LocalDate.of(2023, 12, 30));
        final Price totalPrice = new Price(10000);
        final OrderDetail orderDetail = new OrderDetail(Map.of(Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 2, Menu.BBQ_RIBS, 1));

        // when
        final Discount special = new Special();
        final Price discountPrice = special.discount(visitedDate, totalPrice, orderDetail);

        // then
        Assertions.assertEquals(discountPrice, new Price(0));
    }
}