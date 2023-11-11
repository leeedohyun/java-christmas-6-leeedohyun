package christmas.model;

import java.util.List;

public class Order {

    public int calculateDiscountedPrice(final int totalPrice, final int discountPrice) {
        return totalPrice - discountPrice;
    }

    public int countMainMenu(List<Menu> orderedMenus) {
        return (int) orderedMenus.stream()
                .filter(orderedMenu -> orderedMenu.getMenuType() == MenuType.MAIN)
                .count();
    }

    public int countDessert(List<Menu> orderedMenus) {
        return (int) orderedMenus.stream()
                .filter(orderedMenu -> orderedMenu.getMenuType() == MenuType.DESSERT)
                .count();
    }

    public Money calculateOrderedPriceBeforeDiscount(List<Menu> orderedMenus) {
        return orderedMenus.stream()
                .map(Menu::getPrice)
                .reduce(Money::plus)
                .orElse(new Money(0));
    }
}
