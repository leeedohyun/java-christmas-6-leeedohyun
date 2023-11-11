package christmas.model;

public enum Badge {

    STAR(new Money(5_000)),
    TREE(new Money(10_000)),
    SANTA(new Money(20_000)),
    NONE(new Money(0));

    private final Money standardPrice;

    Badge(final Money standardPrice) {
        this.standardPrice = standardPrice;
    }

    public static Badge decide(final Money totalBenefitPrice) {
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
}
