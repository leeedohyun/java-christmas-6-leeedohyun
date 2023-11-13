package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.MenuType;
import christmas.model.Price;
import christmas.model.OrderDetail;

public class WeekDay implements Discount {

    private static final Price WEEKDAY_DISCOUNT_PRICE = new Price(2_023);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Day visitedDay, final Price totalPrice, final OrderDetail orderDetail) {
        if (visitedDay.isEventNotEnded() && !visitedDay.isWeekend() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return WEEKDAY_DISCOUNT_PRICE.multiply(orderDetail.countNumberOfMenusByMenuType(MenuType.DESSERT));
        }
        return Constants.ZERO_WON;
    }
}
