package christmas.model.order;

import christmas.model.Constants;
import christmas.model.Date;
import christmas.model.GiveawayEvent;
import christmas.model.Price;
import christmas.model.discount.DiscountManager;
import java.util.List;

public class Order {

    private final Date date;
    private final OrderDetail orderDetail;
    private final Price priceBeforeDiscount;
    private final DiscountManager discountManager;
    private final GiveawayEvent giveawayEvent;

    public Order(final Date date, final OrderDetail orderDetail, final Price priceBeforeDiscount,
                 final DiscountManager discountManager, final GiveawayEvent giveawayEvent) {
        this.date = date;
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
        return discountManager.calculateDiscountPrices(date, priceBeforeDiscount, orderDetail);
    }

    public Price calculateTotalBenefitPrice(final Price discountPrice) {
        return discountPrice.plus(giveawayEvent.getGiveawayEventMenuPrice());
    }

    public Price getGiveawayEventMenuPrice() {
        return giveawayEvent.getGiveawayEventMenuPrice();
    }
}
