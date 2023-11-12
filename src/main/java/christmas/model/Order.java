package christmas.model;

import java.util.Collections;
import java.util.Map;

public class Order {

    private static final int MINIMUM_ORDERED_MENUS = 1;
    private static final int MAXIMUM_ORDERED_MENUS = 20;

    private final Map<Menu, Integer> orderedMenus;

    public Order(final Map<Menu, Integer> orderedMenus) {
        validateOrderForBeverages(orderedMenus);
        validateNumberOfOrderedMenus(orderedMenus);
        validateMinimumOrderQuantity(orderedMenus);
        this.orderedMenus = orderedMenus;
    }

    public static Money calculateDiscountedPrice(final Money totalPrice, final Money discountPrice) {
        return totalPrice.minus(discountPrice);
    }

    public int countNumberOfMenusByMenuType(final MenuType menuType) {
        return orderedMenus.keySet().stream()
                .filter(orderedMenu -> orderedMenu.getMenuType() == menuType)
                .mapToInt(orderedMenus::get)
                .sum();
    }

    public Money calculateOrderedPriceBeforeDiscount() {
        return orderedMenus.keySet().stream()
                .map(menu -> menu.calculateMenuPrice(orderedMenus.get(menu)))
                .reduce(Money::plus)
                .orElse(Constants.ZERO_WON);
    }

    public Map<Menu, Integer> getOrderedMenus() {
        return Collections.unmodifiableMap(orderedMenus);
    }

    private void validateOrderForBeverages(final Map<Menu, Integer> orderedMenus) {
        if (containsOnlyBeverages(orderedMenus)) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    private boolean containsOnlyBeverages(final Map<Menu, Integer> orderedMenus) {
        return orderedMenus.keySet().stream()
                .allMatch(menu -> menu.getMenuType() == MenuType.BEVERAGE);
    }

    private void validateNumberOfOrderedMenus(final Map<Menu, Integer> orderedMenus) {
        int numberOfMenus = countNumberOfOrderedMenus(orderedMenus);
        if (isInvalidNumberOfMenus(numberOfMenus)) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    private boolean isInvalidNumberOfMenus(final int numberOfMenus) {
        return numberOfMenus > MAXIMUM_ORDERED_MENUS;
    }

    private int countNumberOfOrderedMenus(final Map<Menu, Integer> orderedMenus) {
        return orderedMenus.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateMinimumOrderQuantity(final Map<Menu, Integer> orderedMenus) {
        if (isInvalidNumberOfMenu(orderedMenus)) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    private boolean isInvalidNumberOfMenu(final Map<Menu, Integer> orderedMenus) {
        return orderedMenus.values().stream()
                .anyMatch(quantity -> quantity < MINIMUM_ORDERED_MENUS);
    }
}
