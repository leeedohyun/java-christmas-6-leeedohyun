package christmas.model;

import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderedMenus;

    public Order(Map<Menu, Integer> orderedMenus) {
        validateOrderForBeverages(orderedMenus);
        this.orderedMenus = orderedMenus;
    }

    public static int calculateDiscountedPrice(final int totalPrice, final int discountPrice) {
        return totalPrice - discountPrice;
    }

    public int countMainMenu() {
        return (int) orderedMenus.keySet().stream()
                .filter(orderedMenu -> orderedMenu.getMenuType() == MenuType.MAIN)
                .count();
    }

    public int countDessert() {
        return (int) orderedMenus.keySet().stream()
                .filter(orderedMenu -> orderedMenu.getMenuType() == MenuType.DESSERT)
                .count();
    }

    public Money calculateOrderedPriceBeforeDiscount() {
        return orderedMenus.keySet().stream()
                .map(Menu::getPrice)
                .reduce(Money::plus)
                .orElse(new Money(0));
    }

    public void validateOrderForBeverages(Map<Menu, Integer> orderedMenus) {
        if (containsOnlyBeverages(orderedMenus)) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    private boolean containsOnlyBeverages(Map<Menu, Integer> orderedMenus) {
        return orderedMenus.keySet().stream()
                .allMatch(menu -> menu.getMenuType() == MenuType.BEVERAGE);
    }
}
