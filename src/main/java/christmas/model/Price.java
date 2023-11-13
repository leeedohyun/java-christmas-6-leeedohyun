package christmas.model;

import java.text.DecimalFormat;
import java.util.Objects;

public class Price {

    private final int money;

    public Price(final int money) {
        this.money = money;
    }

    public Price plus(final Price another) {
        return new Price(money + another.money);
    }

    public Price multiply(final int numberOfMenu) {
        return new Price(money * numberOfMenu);
    }

    public Price minus(final Price another) {
        return new Price(money - another.money);
    }

    public boolean isEqualAndOver(final Price another) {
        return money >= another.money;
    }

    public boolean isUnder(final Price another) {
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
        if (!(o instanceof Price price1)) {
            return false;
        }
        return money == price1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
