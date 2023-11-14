package christmas.model.order;

import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.GiveawayEvent;
import christmas.model.Price;
import christmas.model.discount.DiscountManager;
import java.util.List;

public class Order {

    private final Day day;
    private final OrderDetail orderDetail;
    private final Price priceBeforeDiscount;
    private final DiscountManager discountManager;
    private final GiveawayEvent giveawayEvent;

    public Order(final Day day, final OrderDetail orderDetail, final Price priceBeforeDiscount,
                 final DiscountManager discountManager,
                 final GiveawayEvent giveawayEvent) {
        this.day = day;
        this.orderDetail = orderDetail;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.discountManager = discountManager;
        this.giveawayEvent = giveawayEvent;
    }

    public Price calculateTotalDiscountPrice() {
        final List<Price> discountPrices = getDiscountPrices();
        return discountPrices.stream()
                .reduce(Price::plus)
                .orElse(Constants.ZERO_WON);
    }

    public Price calculateDiscountedPrice(final Price discountPrice) {
        return priceBeforeDiscount.minus(discountPrice);
    }

    public List<Price> getDiscountPrices() {
        return discountManager.calculateDiscountPrices(day, priceBeforeDiscount, orderDetail);
    }

    public Price calculateTotalBenefitPrice() {
        final Price discountPrice = calculateTotalDiscountPrice();
        return discountPrice.plus(giveawayEvent.getGiveawayEventMenuPrice());
    }

    public Price getGiveawayEventMenuPrice() {
        return giveawayEvent.getGiveawayEventMenuPrice();
    }
}
