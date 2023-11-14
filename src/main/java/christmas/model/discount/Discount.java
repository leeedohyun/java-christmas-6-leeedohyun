package christmas.model.discount;

import christmas.model.Day;
import christmas.model.Price;
import christmas.model.order.OrderDetail;

public interface Discount {

    Price discount(final Day visitedDay, final Price totalPrice, final OrderDetail orderDetail);
}
