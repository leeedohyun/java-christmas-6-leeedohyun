package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Date;
import christmas.model.menu.MenuType;
import christmas.model.Price;
import christmas.model.order.OrderDetail;

public class WeekDay implements Discount {

    private static final Price WEEKDAY_DISCOUNT_PRICE = new Price(2_023);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Date dateOfVisit, final Price priceBeforeDiscount, final OrderDetail orderDetail) {
        if (canApply(dateOfVisit, priceBeforeDiscount)) {
            return WEEKDAY_DISCOUNT_PRICE.multiply(orderDetail.countNumberOfMenusByMenuType(MenuType.DESSERT));
        }
        return Constants.ZERO_WON;
    }

    private boolean canApply(final Date dateOfVisit, final Price priceBeforeDiscount) {
        return dateOfVisit.isEventNotEnded() && !dateOfVisit.isWeekend()
                && priceBeforeDiscount.isEqualAndOver(MINIMUM_ORDER_PRICE);
    }
}
