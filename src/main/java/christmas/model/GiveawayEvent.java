package christmas.model;

import christmas.model.menu.Menu;

public class GiveawayEvent {

    public static final int MIN_PRICE_FOR_GIVEAWAY_EVENT = 120_000;
    public static final String GIVEAWAY_EVENT_MENU = "샴페인 1개";

    private final Price giveawayEventMenuPrice;
    private final boolean eventActive;

    private GiveawayEvent(final Price giveawayEventMenuPrice, final boolean eventActive) {
        this.giveawayEventMenuPrice = giveawayEventMenuPrice;
        this.eventActive = eventActive;
    }

    public static GiveawayEvent create(final Price priceBeforeDiscount) {
        if (priceBeforeDiscount.isEqualAndOver(new Price(MIN_PRICE_FOR_GIVEAWAY_EVENT))) {
            return new GiveawayEvent(Menu.getPriceByMenu(Menu.CHAMPAGNE), true);
        }
        return new GiveawayEvent(Constants.ZERO_WON, false);
    }

    public String getGiveawayMenu() {
        if (eventActive) {
            return GIVEAWAY_EVENT_MENU;
        }
        return Constants.NO_BENEFIT_MESSAGE;
    }

    public Price getGiveawayEventMenuPrice() {
        return giveawayEventMenuPrice;
    }
}
