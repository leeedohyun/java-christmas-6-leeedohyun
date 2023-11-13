package christmas.model;

import christmas.model.discount.DiscountManager;
import java.util.List;

public class Orders {

    private final Day day;
    private final Order order;
    private final Money priceBeforeDiscount;
    private final DiscountManager discountManager;
    private final GiveawayEvent giveawayEvent;

    public Orders(final Day day, final Order order, final Money priceBeforeDiscount,
                  final DiscountManager discountManager,
                  final GiveawayEvent giveawayEvent) {
        this.day = day;
        this.order = order;
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
        return discountManager.calculateDiscountPrices(day, priceBeforeDiscount, order);
    }

    public Money calculateTotalBenefitPrice() {
        final Money discountPrice = calculateTotalDiscountPrice();
        return discountPrice.plus(giveawayEvent.getGiveawayEventMenuPrice());
    }

    public Money getGiveawayEventMenuPrice() {
        return giveawayEvent.getGiveawayEventMenuPrice();
    }

    public GiveawayEvent getGiveawayEvent() {
        return giveawayEvent;
    }
}
