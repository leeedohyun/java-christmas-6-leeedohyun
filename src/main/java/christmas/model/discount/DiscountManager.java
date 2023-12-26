package christmas.model.discount;

import java.util.List;

import christmas.model.Date;
import christmas.model.Price;
import christmas.model.order.OrderDetail;

public class DiscountManager {

    private static final List<Discount> DISCOUNT_POLICIES = List.of(new DDay(), new WeekDay(), new Weekend(), new Special());

    private final List<Discount> discountPolicies;

    public DiscountManager(final List<Discount> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public List<Price> calculateDiscountPrices(final Date dateOfVisit, final Price priceBeforeDiscount,
                                               final OrderDetail orderDetail) {
        return discountPolicies.stream()
                .map(discountPolicy -> discountPolicy.discount(dateOfVisit, priceBeforeDiscount, orderDetail))
                .toList();
    }

    public static List<Discount> getDiscountPolicies() {
        return DISCOUNT_POLICIES;
    }
}
