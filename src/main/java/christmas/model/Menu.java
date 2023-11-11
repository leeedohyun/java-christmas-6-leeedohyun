package christmas.model;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", new Money(6_000), MenuType.APPETIZER),
    TAPAS("타파스", new Money(5_500), MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", new Money(8_000), MenuType.APPETIZER),
    T_BONE_STEAK("티본스테이크", new Money(55_000), MenuType.MAIN),
    BBQ_RIBS("바비큐립", new Money(54_000), MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", new Money(35_000), MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", new Money(25_000), MenuType.MAIN),
    CHOCOLATE_CAKE("초코케이크", new Money(15_000), MenuType.DESSERT),
    ICE_CREAM("아이스크림", new Money(5_000), MenuType.DESSERT),
    ZERO_COLA("제로콜라", new Money(3_000), MenuType.BEVERAGE),
    RED_WINE("레드와인", new Money(6_0000), MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", new Money(25_000), MenuType.BEVERAGE);

    private final String name;
    private final Money price;
    private final MenuType menuType;

    Menu(String name, Money price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public MenuType getMenuType() {
        return menuType;
    }
}