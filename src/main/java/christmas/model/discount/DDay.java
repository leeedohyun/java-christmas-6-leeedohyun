package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Date;
import christmas.model.Price;
import christmas.model.order.OrderDetail;

public class DDay implements Discount {

    private static final Price BASIC_DISCOUNT_PRICE = new Price(1_000);
    private static final Price ADDITIONAL_DISCOUNT_PRICE = new Price(100);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Date dateOfVisit, final Price priceBeforeDiscount, final OrderDetail orderDetail) {
        if (canApply(dateOfVisit, priceBeforeDiscount)) {
            return BASIC_DISCOUNT_PRICE.plus(
                    ADDITIONAL_DISCOUNT_PRICE.multiply(dateOfVisit.calculateDaysUntilEventStart()));
        }
        return Constants.ZERO_WON;
    }

    private boolean canApply(final Date dateOfVisit, final Price priceBeforeDiscount) {
        return dateOfVisit.isBeforeOrEqualChristmas() && priceBeforeDiscount.isEqualAndOver(MINIMUM_ORDER_PRICE);
    }
}
