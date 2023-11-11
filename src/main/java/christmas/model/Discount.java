package christmas.model;

public class Discount {

    private static final Money D_DAY_BASIC_DISCOUNT_PRICE = new Money(1_000);
    private static final Money D_DAY_ADDITIONAL_DISCOUNT_PRICE = new Money(100);
    private static final Money WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE = new Money(2_023);
    private static final Money SPECIAL_DISCOUNT_PRICE = new Money(1_000);
    private static final Money MINIMUM_ORDER_PRICE = new Money(10_000);

    public Money discountDDay(final Day visitedDay, final Money totalPrice) {
        if (visitedDay.isBeforeOrEqualChristmas() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return D_DAY_BASIC_DISCOUNT_PRICE.plus(
                    D_DAY_ADDITIONAL_DISCOUNT_PRICE.multiply(visitedDay.calculateDaysUntilEventStart()));
        }
        return Constants.ZERO_WON;
    }

    public Money discountWeekDay(final Day visitedDay, final Money totalPrice, final int numberOfDessertMenus) {
        if (visitedDay.isEventNotEnded() && !visitedDay.isWeekend() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE.multiply(numberOfDessertMenus);
        }
        return Constants.ZERO_WON;
    }

    public Money discountWeekend(final Day visitedDay, final Money totalPrice, final int numberOfMainMenu) {
        if (visitedDay.isEventNotEnded() && visitedDay.isWeekend() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE.multiply(numberOfMainMenu);
        }
        return Constants.ZERO_WON;
    }

    public Money discountSpecial(final Day visitedDay, final Money totalPrice) {
        if (visitedDay.isEventNotEnded() && visitedDay.hasStar() && totalPrice.isEqualAndOver(MINIMUM_ORDER_PRICE)) {
            return SPECIAL_DISCOUNT_PRICE;
        }
        return Constants.ZERO_WON;
    }
}
