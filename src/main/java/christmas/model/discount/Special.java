package christmas.model.discount;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.Money;

public class Special implements Discount {

    private static final Money SPECIAL_DISCOUNT_PRICE = new Money(1_000);
    private static final Money MINIMUM_ORDER_PRICE = new Money(10_000);

    @Override
    public Money discount(Day visitedDay, Money totalPrice, int numberOfMenus) {
        if (visitedDay.isEventNotEnded() && visitedDay.hasStar() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return SPECIAL_DISCOUNT_PRICE;
        }
        return Constants.ZERO_WON;
    }
}
