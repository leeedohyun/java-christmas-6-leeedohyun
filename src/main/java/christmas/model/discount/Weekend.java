package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.Money;

public class Weekend implements Discount {

    private static final Money WEEKEND_DISCOUNT_PRICE = new Money(2_023);
    private static final Money MINIMUM_ORDER_PRICE = new Money(10_000);

    @Override
    public Money discount(final Day visitedDay, final Money totalPrice, final int numberOfMenus) {
        if (visitedDay.isEventNotEnded() && visitedDay.isWeekend() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return WEEKEND_DISCOUNT_PRICE.multiply(numberOfMenus);
        }
        return Constants.ZERO_WON;
    }
}
