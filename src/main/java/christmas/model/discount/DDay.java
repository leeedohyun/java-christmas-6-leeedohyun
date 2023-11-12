package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.Money;

public class DDay implements Discount {

    private static final Money D_DAY_BASIC_DISCOUNT_PRICE = new Money(1_000);
    private static final Money D_DAY_ADDITIONAL_DISCOUNT_PRICE = new Money(100);
    private static final Money MINIMUM_ORDER_PRICE = new Money(10_000);

    @Override
    public Money discount(final Day visitedDay, final Money totalPrice, final int numberOfMenus) {
        if (visitedDay.isBeforeOrEqualChristmas() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return D_DAY_BASIC_DISCOUNT_PRICE.plus(
                    D_DAY_ADDITIONAL_DISCOUNT_PRICE.multiply(visitedDay.calculateDaysUntilEventStart()));
        }
        return Constants.ZERO_WON;
    }
}
