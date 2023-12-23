package christmas.util;

import java.text.DecimalFormat;

import christmas.model.Price;

public class ViewFormatter {

    private static final String MONEY_FORMAT_PATTERN = "###,###";

    private ViewFormatter() {
    }

    public static String getFormattedMoney(final Price price) {
        final DecimalFormat decimalFormat = new DecimalFormat(MONEY_FORMAT_PATTERN);
        return decimalFormat.format(price.getPrice());
    }
}
