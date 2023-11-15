package christmas.model;

import java.text.DecimalFormat;
import java.util.Objects;

public class Price {

    private static final String MONEY_FORMAT_PATTERN = "###,###";
    public static final String INVALID_PRICE_EXCEPTION = "[ERROR] 가격은 음수일 수 없습니다.";

    private final int price;

    public Price(final int price) {
        this.price = price;
    }

    public Price plus(final Price another) {
        return new Price(price + another.price);
    }

    public Price multiply(final int numberOfMenu) {
        return new Price(price * numberOfMenu);
    }

    public Price minus(final Price another) {
        int minussedPrice = price - another.price;
        if (minussedPrice < 0) {
            throw new IllegalArgumentException(INVALID_PRICE_EXCEPTION);
        }
        return new Price(minussedPrice);
    }

    public boolean isEqualAndOver(final Price another) {
        return price >= another.price;
    }

    public boolean isUnder(final Price another) {
        return price < another.price;
    }

    public String getFormattedMoney() {
        DecimalFormat decimalFormat = new DecimalFormat(MONEY_FORMAT_PATTERN);
        return decimalFormat.format(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price1 = (Price) o;
        return price == price1.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
