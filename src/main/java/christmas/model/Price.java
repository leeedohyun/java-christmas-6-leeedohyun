package christmas.model;

import java.util.Objects;

public class Price {

    private static final String INVALID_PRICE_EXCEPTION = "가격은 음수일 수 없습니다.";

    private final int price;

    public Price(final int price) {
        validate(price);
        this.price = price;
    }

    public Price plus(final Price another) {
        return new Price(price + another.price);
    }

    public Price multiply(final int numberOfMenu) {
        return new Price(price * numberOfMenu);
    }

    public Price minus(final Price another) {
        final int minussedPrice = price - another.price;
        return new Price(minussedPrice);
    }

    public boolean isEqualAndOver(final Price another) {
        return price >= another.price;
    }

    public boolean isWithinRange(final Price minPrice, final Price maxPrice) {
        return this.price >= minPrice.price && this.price < maxPrice.price;
    }

    public int getPrice() {
        return price;
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

    private void validate(final int minusedPrice) {
        if (minusedPrice < 0) {
            throw new IllegalArgumentException(INVALID_PRICE_EXCEPTION);
        }
    }
}
