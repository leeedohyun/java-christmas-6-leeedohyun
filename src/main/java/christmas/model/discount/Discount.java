package christmas.model.discount;

import christmas.model.Day;
import christmas.model.Money;
import christmas.model.OrderDetail;

public interface Discount {

    Money discount(final Day visitedDay, final Money totalPrice, final OrderDetail orderDetail);
}
