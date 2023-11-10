package christmas.model;

public class Order {

    public int calculateDiscountedPrice(final int totalPrice, final int discountPrice) {
        return totalPrice - discountPrice;
    }
}
