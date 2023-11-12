package christmas.model.discount;

import christmas.model.Day;
import christmas.model.Money;
import christmas.model.Order;
import java.util.List;

public class DiscountManager {

    private final List<Discount> discountPolicies;

    public DiscountManager() {
        this.discountPolicies = List.of(new DDay(), new WeekDay(), new Weekend(), new Special());
    }

    public List<Money> calculateTotalDiscountPrice(Day visitedDay, Money totalPrice, Order order) {
        return discountPolicies.stream()
                .map(discountPolicy -> discountPolicy.discount(visitedDay, totalPrice, order))
                .toList();
    }
}
