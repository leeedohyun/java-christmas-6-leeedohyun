package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.Price;
import christmas.model.OrderDetail;

public class DDay implements Discount {

    private static final Price D_DAY_BASIC_DISCOUNT_PRICE = new Price(1_000);
    private static final Price D_DAY_ADDITIONAL_DISCOUNT_PRICE = new Price(100);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Day visitedDay, final Price totalPrice, final OrderDetail orderDetail) {
        if (visitedDay.isBeforeOrEqualChristmas() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return D_DAY_BASIC_DISCOUNT_PRICE.plus(
                    D_DAY_ADDITIONAL_DISCOUNT_PRICE.multiply(visitedDay.calculateDaysUntilEventStart()));
        }
        return Constants.ZERO_WON;
    }
}
