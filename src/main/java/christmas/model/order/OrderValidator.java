package christmas.model.order;

import java.util.Map;

import christmas.exception.MenuNotFoundException;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;

public class OrderValidator {

    private static final int MINIMUM_ORDERED_MENUS = 1;
    private static final int MAXIMUM_ORDERED_MENUS = 20;

    private OrderValidator() {
    }

    public static void validateOrder(final Map<Menu, Integer> orderedMenus) {
        validateOrderForBeverages(orderedMenus);
        validateNumberOfOrderedMenus(orderedMenus);
        validateMinimumOrderQuantity(orderedMenus);
    }

    private static void validateOrderForBeverages(final Map<Menu, Integer> orderedMenus) {
        if (containsOnlyBeverages(orderedMenus)) {
            throw new MenuNotFoundException();
        }
    }

    private static boolean containsOnlyBeverages(final Map<Menu, Integer> orderedMenus) {
        return orderedMenus.keySet().stream()
                .allMatch(menu -> menu.isMenuTypeMatch(MenuType.BEVERAGE));
    }

    private static void validateNumberOfOrderedMenus(final Map<Menu, Integer> orderedMenus) {
        final int numberOfMenus = countNumberOfOrderedMenus(orderedMenus);
        if (isInvalidNumberOfMenus(numberOfMenus)) {
            throw new MenuNotFoundException();
        }
    }

    private static boolean isInvalidNumberOfMenus(final int numberOfMenus) {
        return numberOfMenus > MAXIMUM_ORDERED_MENUS;
    }

    private static int countNumberOfOrderedMenus(final Map<Menu, Integer> orderedMenus) {
        return orderedMenus.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void validateMinimumOrderQuantity(final Map<Menu, Integer> orderedMenus) {
        if (isInvalidNumberOfMenu(orderedMenus)) {
            throw new MenuNotFoundException();
        }
    }

    private static boolean isInvalidNumberOfMenu(final Map<Menu, Integer> orderedMenus) {
        return orderedMenus.values().stream()
                .anyMatch(quantity -> quantity < MINIMUM_ORDERED_MENUS);
    }
}
