package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.Price;
import christmas.model.OrderDetail;

public class Special implements Discount {

    private static final Price SPECIAL_DISCOUNT_PRICE = new Price(1_000);
    private static final Price MINIMUM_ORDER_PRICE = new Price(10_000);

    @Override
    public Price discount(final Day visitedDay, final Price totalPrice, final OrderDetail orderDetail) {
        if (visitedDay.isEventNotEnded() && visitedDay.hasStar() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return SPECIAL_DISCOUNT_PRICE;
        }
        return Constants.ZERO_WON;
    }
}
