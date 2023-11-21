package christmas.model;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    NONE("없음", 0);

    private final String name;
    private final Price standardPrice;

    Badge(final String name, final int standardPrice) {
        this.name = name;
        this.standardPrice = new Price(standardPrice);
    }

    public static Badge decide(final Price totalBenefitPrice) {
        if (totalBenefitPrice.isEqualAndOver(STAR.standardPrice) && totalBenefitPrice.isUnder(TREE.standardPrice)) {
            return STAR;
        }
        if (totalBenefitPrice.isEqualAndOver(TREE.standardPrice) && totalBenefitPrice.isUnder(SANTA.standardPrice)) {
            return TREE;
        }
        if (totalBenefitPrice.isEqualAndOver(SANTA.standardPrice)) {
            return SANTA;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
