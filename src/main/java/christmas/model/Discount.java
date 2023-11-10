package christmas.model;

public class Discount {

    private static final int EVENT_START_DAY = 1;
    private static final int CHRISTMAS = 25;
    private static final int D_DAY_BASIC_DISCOUNT_PRICE = 1000;
    private static final int D_DAY_ADDITIONAL_DISCOUNT_PRICE = 100;
    private static final int EVENT_END_DATE = 31;
    private static final int MINIMUM_ORDER_PRICE = 10000;
    private static final int WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE = 2023;

    public int discountDDay(int visitedDay, int totalPrice) {
        if (visitedDay <= CHRISTMAS && totalPrice >= MINIMUM_ORDER_PRICE) {
            return D_DAY_BASIC_DISCOUNT_PRICE + (visitedDay - EVENT_START_DAY) * D_DAY_ADDITIONAL_DISCOUNT_PRICE;
        }
        return 0;
    }

    public int discountWeekDay(int visitedDay, int totalPrice, int numberOfDessertMenus) {
        if (visitedDay <= EVENT_END_DATE && totalPrice >= MINIMUM_ORDER_PRICE) {
            return WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE * numberOfDessertMenus;
        }
        return 0;
    }
}
