package christmas.model;

public enum Badge {

    STAR("별", new Price(5_000)),
    TREE("트리", new Price(10_000)),
    SANTA("산타", new Price(20_000)),
    NONE("없음", Constants.ZERO_WON);

    private final String name;
    private final Price standardPrice;

    Badge(final String name, final Price standardPrice) {
        this.name = name;
        this.standardPrice = standardPrice;
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
