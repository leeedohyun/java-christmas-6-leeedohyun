package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.MenuType;
import christmas.model.Money;
import christmas.model.OrderDetail;

public class Weekend implements Discount {

    private static final Money WEEKEND_DISCOUNT_PRICE = new Money(2_023);
    private static final Money MINIMUM_ORDER_PRICE = new Money(10_000);

    @Override
    public Money discount(final Day visitedDay, final Money totalPrice, final OrderDetail orderDetail) {
        if (visitedDay.isEventNotEnded() && visitedDay.isWeekend() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return WEEKEND_DISCOUNT_PRICE.multiply(orderDetail.countNumberOfMenusByMenuType(MenuType.MAIN));
        }
        return Constants.ZERO_WON;
    }
}
