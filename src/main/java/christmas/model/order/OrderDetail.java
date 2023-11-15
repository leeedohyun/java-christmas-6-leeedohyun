package christmas.model.order;

import christmas.model.Constants;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.Price;
import java.util.Collections;
import java.util.Map;

public class OrderDetail {

    private final Map<Menu, Integer> orderedMenus;

    public OrderDetail(final Map<Menu, Integer> orderedMenus) {
        OrderValidator.validateOrder(orderedMenus);
        this.orderedMenus = orderedMenus;
    }

    public int countNumberOfMenusByMenuType(final MenuType menuType) {
        return orderedMenus.keySet().stream()
                .filter(orderedMenu -> orderedMenu.getMenuType() == menuType)
                .mapToInt(orderedMenus::get)
                .sum();
    }

    public Price calculateOrderedPriceBeforeDiscount() {
        return orderedMenus.keySet().stream()
                .map(menu -> menu.calculateMenuPrice(orderedMenus.get(menu)))
                .reduce(Price::plus)
                .orElse(Constants.ZERO_WON);
    }

    public Map<Menu, Integer> getOrderedMenus() {
        return Collections.unmodifiableMap(orderedMenus);
    }
}
