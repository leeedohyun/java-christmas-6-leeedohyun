package christmas.model.order;

import java.util.List;

import christmas.model.Constants;
import christmas.model.GiveawayEvent;
import christmas.model.Price;
import christmas.model.discount.DiscountManager;

public class Order {

    private final DiscountManager discountManager;
    private final OrderHistory orderHistory;
    private final GiveawayEvent giveawayEvent;

    public Order(final DiscountManager discountManager, final OrderHistory orderHistory,
                 final GiveawayEvent giveawayEvent) {
        this.discountManager = discountManager;
        this.orderHistory = orderHistory;
        this.giveawayEvent = giveawayEvent;
    }

    public Price calculateTotalDiscountPrice() {
        final List<Price> discountPrices = getDiscountPrices();
        return discountPrices.stream()
                .reduce(Price::plus)
                .orElse(Constants.ZERO_WON);
    }

    public Price calculateDiscountedPrice(final Price discountPrice) {
        return getPriceBeforeDiscount().minus(discountPrice);
    }

    public List<Price> getDiscountPrices() {
        return discountManager.calculateDiscountPrices(orderHistory.getDate(), getPriceBeforeDiscount(),
                orderHistory.getOrderDetail());
    }

    public Price calculateTotalBenefitPrice(final Price discountPrice) {
        return discountPrice.plus(giveawayEvent.getGiveawayEventMenuPrice());
    }

    public Price getGiveawayEventMenuPrice() {
        return giveawayEvent.getGiveawayEventMenuPrice();
    }

    private Price getPriceBeforeDiscount() {
        return orderHistory.getPriceBeforeDiscount();
    }
}
