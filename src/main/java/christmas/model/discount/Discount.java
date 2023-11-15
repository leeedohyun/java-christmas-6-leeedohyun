package christmas.model.discount;

import christmas.model.Date;
import christmas.model.Price;
import christmas.model.order.OrderDetail;

public interface Discount {

    Price discount(final Date dateOfVisit, final Price priceBeforeDiscount, final OrderDetail orderDetail);
}
