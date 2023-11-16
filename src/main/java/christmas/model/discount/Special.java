package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Date;
import christmas.model.Price;
import christmas.model.order.OrderDetail;

public class Special implements Discount {

    private static final Price SPECIAL_DISCOUNT_PRICE = new Price(1_000);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Date dateOfVisit, final Price priceBeforeDiscount, final OrderDetail orderDetail) {
        if (dateOfVisit.isEventNotEnded() && dateOfVisit.hasStar()
                && priceBeforeDiscount.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return SPECIAL_DISCOUNT_PRICE;
        }
        return Constants.ZERO_WON;
    }
}
