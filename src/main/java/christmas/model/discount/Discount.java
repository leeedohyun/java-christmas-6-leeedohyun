package christmas.model.discount;

import christmas.model.Day;
import christmas.model.Money;

public interface Discount {

    Money discount(final Day visitedDay, final Money totalPrice, final int numberOfMenus);
}
