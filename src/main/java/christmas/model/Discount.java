package christmas.model;

public class Discount {

    private static final int D_DAY_BASIC_DISCOUNT_PRICE = 1000;
    private static final int D_DAY_ADDITIONAL_DISCOUNT_PRICE = 100;
    private static final int MINIMUM_ORDER_PRICE = 10000;
    private static final int WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE = 2023;
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;

    public int discountDDay(final Day visitedDay, final int totalPrice) {
        if (visitedDay.isBeforeOrEqualChristmas() && totalPrice >= MINIMUM_ORDER_PRICE) {
            return D_DAY_BASIC_DISCOUNT_PRICE
                    + visitedDay.calculateDaysUntilEventStart() * D_DAY_ADDITIONAL_DISCOUNT_PRICE;
        }
        return 0;
    }

    public int discountWeekDay(final Day visitedDay, final int totalPrice, final int numberOfDessertMenus) {
        if (visitedDay.isEventNotEnded() && !visitedDay.isWeekend() && totalPrice >= MINIMUM_ORDER_PRICE) {
            return WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE * numberOfDessertMenus;
        }
        return 0;
    }

    public int discountWeekend(final Day visitedDay, final int totalPrice, final int numberOfMainMenu) {
        if (visitedDay.isEventNotEnded() && visitedDay.isWeekend() && totalPrice >= MINIMUM_ORDER_PRICE) {
            return WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE * numberOfMainMenu;
        }
        return 0;
    }

    public int discountSpecial(final Day visitedDay, final int totalPrice) {
        if (visitedDay.isEventNotEnded() && visitedDay.hasStar() && totalPrice >= MINIMUM_ORDER_PRICE) {
            return SPECIAL_DISCOUNT_PRICE;
        }
        return 0;
    }
}
