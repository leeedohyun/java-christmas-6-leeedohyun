package christmas.model;

public class Discount {

    private static final int FIRST_DAY = 1;
    private static final int CHRISTMAS = 25;
    private static final int BASIC_DISCOUNT_PRICE = 1000;
    private static final int ADDITIONAL_DISCOUNT_PRICE = 100;

    public int discountDDay(int visitedDay, int totalPrice) {
        if (visitedDay <= CHRISTMAS && totalPrice >= 10000) {
            return BASIC_DISCOUNT_PRICE + (visitedDay - FIRST_DAY) * ADDITIONAL_DISCOUNT_PRICE;
        }
        return 0;
    }
}
