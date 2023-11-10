package christmas.model;

public class Order {

    public int calculateDiscountedPrice(int totalPrice, int discountPrice) {
        return totalPrice - discountPrice;
    }
}
