package christmas.model;

import java.util.Collections;
import java.util.Map;

public class OrderDetail {

    private final Map<Menu, Integer> orderedMenus;

    public OrderDetail(final Map<Menu, Integer> orderedMenus) {
        OrderValidator.validateOrder(orderedMenus);
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
}
