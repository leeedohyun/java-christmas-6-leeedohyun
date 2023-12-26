package christmas.model.discount;

import christmas.model.Date;
import christmas.model.Price;
import christmas.model.menu.MenuType;
import christmas.model.order.OrderDetail;

public class Weekend implements Discount {

    private static final Price WEEKEND_DISCOUNT_PRICE = new Price(2_023);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Date dateOfVisit, final Price priceBeforeDiscount, final OrderDetail orderDetail) {
        if (canApply(dateOfVisit, priceBeforeDiscount)) {
            return WEEKEND_DISCOUNT_PRICE.multiply(orderDetail.countNumberOfMenusByMenuType(MenuType.MAIN));
        }
        return Price.createZeroWon();
    }

    private boolean canApply(final Date dateOfVisit, final Price priceBeforeDiscount) {
        return dateOfVisit.isWeekend() && priceBeforeDiscount.isEqualAndOver(MINIMUM_ORDER_PRICE);
    }
}
