package christmas.model.discount;

import christmas.model.Day;
import christmas.model.Price;
import christmas.model.order.OrderDetail;
import java.util.List;

public class DiscountManager {

    private final List<Discount> discountPolicies;

    public DiscountManager() {
        this.discountPolicies = List.of(new DDay(), new WeekDay(), new Weekend(), new Special());
    }

    public List<Price> calculateDiscountPrices(final Day visitedDay, final Price totalPrice,
                                               final OrderDetail orderDetail) {
        return discountPolicies.stream()
                .map(discountPolicy -> discountPolicy.discount(visitedDay, totalPrice, orderDetail))
                .toList();
    }
}
