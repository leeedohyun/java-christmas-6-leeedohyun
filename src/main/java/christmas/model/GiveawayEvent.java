package christmas.model;

import christmas.model.menu.Menu;

public class GiveawayEvent {

    private static final int MIN_PRICE_FOR_GIVEAWAY_EVENT = 120_000;

    private final Price giveawayEventMenuPrice;

    private GiveawayEvent(final Price giveawayEventMenuPrice) {
        this.giveawayEventMenuPrice = giveawayEventMenuPrice;
    }

    public static GiveawayEvent create(final Price priceBeforeDiscount) {
        if (priceBeforeDiscount.isEqualAndOver(new Price(MIN_PRICE_FOR_GIVEAWAY_EVENT))) {
            return new GiveawayEvent(Menu.CHAMPAGNE.getPrice());
        }
        return new GiveawayEvent(Constants.ZERO_WON);
    }

    public boolean canGetGiveawayEventMenu() {
        return giveawayEventMenuPrice.equals(Menu.CHAMPAGNE.getPrice());
    }

    public Price getGiveawayEventMenuPrice() {
        return giveawayEventMenuPrice;
    }
}
