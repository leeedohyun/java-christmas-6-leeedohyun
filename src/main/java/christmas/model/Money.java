package christmas.model;

public class Money {

    private static final int MINIMUM_ORDER_PRICE = 10000;

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public boolean isTotalPriceOverTenThousand() {
        return money >= MINIMUM_ORDER_PRICE;
    }
}
