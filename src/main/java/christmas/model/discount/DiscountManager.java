package christmas.model.discount;

import christmas.model.Date;
import christmas.model.Price;
import christmas.model.order.OrderDetail;
import java.util.List;

public class DiscountManager {

    private final List<Discount> discountPolicies;

    public DiscountManager() {
        this.discountPolicies = List.of(new DDay(), new WeekDay(), new Weekend(), new Special());
    }

    public List<Price> calculateDiscountPrices(final Date dateOfVisit, final Price priceBeforeDiscount,
                                               final OrderDetail orderDetail) {
        return discountPolicies.stream()
                .map(discountPolicy -> discountPolicy.discount(dateOfVisit, priceBeforeDiscount, orderDetail))
                .toList();
    }
}
