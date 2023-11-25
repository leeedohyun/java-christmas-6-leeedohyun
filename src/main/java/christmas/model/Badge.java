package christmas.model;

public enum Badge {

    STAR("별", 5_000, 10_000),
    TREE("트리", 10_000, 20_000),
    SANTA("산타", 20_000, Integer.MAX_VALUE),
    NONE("없음", 0, 5_000);

    private final String name;
    private final Price minPrice;
    private final Price maxPrice;

    Badge(final String name, final int minPrice, final int maxPrice) {
        this.name = name;
        this.minPrice = new Price(minPrice);
        this.maxPrice = new Price(maxPrice);
    }

    public static Badge decide(final Price totalBenefitPrice) {
        if (totalBenefitPrice.isWithinRange(STAR.minPrice, STAR.maxPrice)) {
            return STAR;
        }
        if (totalBenefitPrice.isWithinRange(TREE.minPrice, TREE.maxPrice)) {
            return TREE;
        }
        if (totalBenefitPrice.isWithinRange(SANTA.minPrice, SANTA.maxPrice)) {
            return SANTA;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
