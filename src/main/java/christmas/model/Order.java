package christmas.model;

import christmas.model.discount.DiscountManager;
import java.util.List;

public class Order {

    private final Day day;
    private final OrderDetail orderDetail;
    private final Money priceBeforeDiscount;
    private final DiscountManager discountManager;
    private final GiveawayEvent giveawayEvent;

    public Order(final Day day, final OrderDetail orderDetail, final Money priceBeforeDiscount,
                 final DiscountManager discountManager,
                 final GiveawayEvent giveawayEvent) {
        this.day = day;
        this.orderDetail = orderDetail;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.discountManager = discountManager;
        this.giveawayEvent = giveawayEvent;
    }

    public Money calculateTotalDiscountPrice() {
        final List<Money> discountPrices = getDiscountPrices();
        return discountPrices.stream()
                .reduce(Money::plus)
                .orElse(Constants.ZERO_WON);
    }

    public Money calculateDiscountedPrice(final Money discountPrice) {
        return priceBeforeDiscount.minus(discountPrice);
    }

    public List<Money> getDiscountPrices() {
        return discountManager.calculateDiscountPrices(day, priceBeforeDiscount, orderDetail);
    }

    public Money calculateTotalBenefitPrice() {
        final Money discountPrice = calculateTotalDiscountPrice();
        return discountPrice.plus(giveawayEvent.getGiveawayEventMenuPrice());
    }

    public Money getGiveawayEventMenuPrice() {
        return giveawayEvent.getGiveawayEventMenuPrice();
    }
}
