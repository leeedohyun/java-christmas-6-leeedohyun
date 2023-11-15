package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Date;
import christmas.model.MenuType;
import christmas.model.Price;
import christmas.model.order.OrderDetail;

public class Weekend implements Discount {

    private static final Price WEEKEND_DISCOUNT_PRICE = new Price(2_023);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Date dateOfVisit, final Price priceBeforeDiscount, final OrderDetail orderDetail) {
        if (dateOfVisit.isEventNotEnded() && dateOfVisit.isWeekend()
                && priceBeforeDiscount.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return WEEKEND_DISCOUNT_PRICE.multiply(orderDetail.countNumberOfMenusByMenuType(MenuType.MAIN));
        }
        return Constants.ZERO_WON;
    }
}
