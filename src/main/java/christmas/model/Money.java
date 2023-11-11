package christmas.model;

import java.text.DecimalFormat;
import java.util.Objects;

public class Money {

    private static final int MINIMUM_ORDER_PRICE = 10000;

    private final int money;

    public Money(final int money) {
        this.money = money;
    }

    public Money plus(final Money another) {
        return new Money(money + another.money);
    }

    public Money multiply(final int numberOfMenu) {
        return new Money(money * numberOfMenu);
    }

    public boolean isOverTenThousand() {
        return money >= MINIMUM_ORDER_PRICE;
    }

    public boolean isEqualAndOver(final Money another) {
        return money >= another.money;
    }

    public boolean isUnder(final Money another) {
        return money < another.money;
    }

    public String getFormattedMoney() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(money);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money money1)) {
            return false;
        }
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
