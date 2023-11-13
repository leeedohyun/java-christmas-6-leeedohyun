package christmas.model;

public class GiveawayEvent {

    private final Price giveawayEventMenuPrice;
    private final boolean eventActive;

    private GiveawayEvent(final Price giveawayEventMenuPrice, final boolean eventActive) {
        this.giveawayEventMenuPrice = giveawayEventMenuPrice;
        this.eventActive = eventActive;
    }

    public static GiveawayEvent create(final Price priceBeforeDiscount) {
        if (priceBeforeDiscount.isEqualAndOver(new Price(120_000))) {
            return new GiveawayEvent(Menu.getPriceByMenu(Menu.CHAMPAGNE), true);
        }
        return new GiveawayEvent(Constants.ZERO_WON, false);
    }

    public String getGiveawayMenu() {
        if (eventActive) {
            return "샴페인 1개";
        }
        return "없음";
    }

    public Price getGiveawayEventMenuPrice() {
        return giveawayEventMenuPrice;
    }

    public boolean isEventActive() {
        return eventActive;
    }
}
