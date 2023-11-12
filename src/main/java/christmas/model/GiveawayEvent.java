package christmas.model;

public class GiveawayEvent {

    private final boolean eventActive;

    private GiveawayEvent(boolean eventActive) {
        this.eventActive = eventActive;
    }

    public static GiveawayEvent create(Money priceBeforeDiscount) {
        if (priceBeforeDiscount.isEqualAndOver(new Money(120_000))) {
            return new GiveawayEvent(true);
        }
        return new GiveawayEvent(false);
    }

    public String getGiveawayMenu() {
        if (eventActive) {
            return "샴페인 1개";
        }
        return "없음";
    }

    public Money getGiveawayMenuPrice() {
        if (eventActive) {
            return Menu.getPriceByMenu(Menu.CHAMPAGNE);
        }
        return Constants.ZERO_WON;
    }

    public boolean isEventActive() {
        return eventActive;
    }
}
