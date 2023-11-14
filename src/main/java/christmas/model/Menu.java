package christmas.model;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", new Price(6_000), MenuType.APPETIZER),
    TAPAS("타파스", new Price(5_500), MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", new Price(8_000), MenuType.APPETIZER),
    T_BONE_STEAK("티본스테이크", new Price(55_000), MenuType.MAIN),
    BBQ_RIBS("바비큐립", new Price(54_000), MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", new Price(35_000), MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", new Price(25_000), MenuType.MAIN),
    CHOCOLATE_CAKE("초코케이크", new Price(15_000), MenuType.DESSERT),
    ICE_CREAM("아이스크림", new Price(5_000), MenuType.DESSERT),
    ZERO_COLA("제로콜라", new Price(3_000), MenuType.BEVERAGE),
    RED_WINE("레드와인", new Price(6_0000), MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", new Price(25_000), MenuType.BEVERAGE);

    private final String name;
    private final Price price;
    private final MenuType menuType;

    Menu(final String name, final Price price, final MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Menu getMenuByName(final String name) {
        validateMenuPresence(name);
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public static Price getPriceByMenu(Menu menu) {
        return menu.price;
    }

    public Price calculateMenuPrice(final int quantity) {
        return price.multiply(quantity);
    }

    public String getName() {
        return name;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    private static void validateMenuPresence(final String menu) {
        if (hasNotMenu(menu)) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    private static boolean hasNotMenu(final String menu) {
        return Arrays.stream(values())
                .noneMatch(value -> value.name.equals(menu));
    }
}
