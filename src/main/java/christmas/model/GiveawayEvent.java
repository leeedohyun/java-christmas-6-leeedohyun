package christmas.model;

public class GiveawayEvent {

    private final Money giveawayEventMenuPrice;
    private final boolean eventActive;

    private GiveawayEvent(final Money giveawayEventMenuPrice, final boolean eventActive) {
        this.giveawayEventMenuPrice = giveawayEventMenuPrice;
        this.eventActive = eventActive;
    }

    public static GiveawayEvent create(final Money priceBeforeDiscount) {
        if (priceBeforeDiscount.isEqualAndOver(new Money(120_000))) {
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

    public Money getGiveawayEventMenuPrice() {
        return giveawayEventMenuPrice;
    }

    public boolean isEventActive() {
        return eventActive;
    }
}
