package christmas.util;

import java.util.Optional;

public class StringToIntConvertor {

    private StringToIntConvertor() {
    }

    static Optional<Integer> convert(final String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (final NumberFormatException numberFormatException) {
            return Optional.empty();
        }
    }
}
