package christmas.model;

public class Discount {

    private static final int D_DAY_BASIC_DISCOUNT_PRICE = 1000;
    private static final int D_DAY_ADDITIONAL_DISCOUNT_PRICE = 100;
    private static final int WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE = 2023;
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;

    public Money discountDDay(final Day visitedDay, final Money totalPrice) {
        if (visitedDay.isBeforeOrEqualChristmas() && totalPrice.isOverTenThousand()) {
            int dDayDiscountPrice = D_DAY_BASIC_DISCOUNT_PRICE
                    + visitedDay.calculateDaysUntilEventStart() * D_DAY_ADDITIONAL_DISCOUNT_PRICE;
            return new Money(dDayDiscountPrice);
        }
        return new Money(0);
    }

    public Money discountWeekDay(final Day visitedDay, final Money totalPrice, final int numberOfDessertMenus) {
        if (visitedDay.isEventNotEnded() && !visitedDay.isWeekend() && totalPrice.isOverTenThousand()) {
            int weekDayDiscountPrice = WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE * numberOfDessertMenus;
            return new Money(weekDayDiscountPrice);
        }
        return new Money(0);
    }

    public Money discountWeekend(final Day visitedDay, final Money totalPrice, final int numberOfMainMenu) {
        if (visitedDay.isEventNotEnded() && visitedDay.isWeekend() && totalPrice.isOverTenThousand()) {
            int weekendDiscountPrice = WEEKDAY_AND_WEEKEND_DISCOUNT_PRICE * numberOfMainMenu;
            return new Money(weekendDiscountPrice);
        }
        return new Money(0);
    }

    public Money discountSpecial(final Day visitedDay, final Money totalPrice) {
        if (visitedDay.isEventNotEnded() && visitedDay.hasStar() && totalPrice.isOverTenThousand()) {
            return new Money(SPECIAL_DISCOUNT_PRICE);
        }
        return new Money(0);
    }
}
