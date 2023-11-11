package christmas.model;

import java.util.Arrays;
import java.util.List;

public final class Utils {

    private static final String DATE_FORMAT_EXCEPTION_MESSAGE = "[ERROR] 날짜는 숫자만 입력할 수 있습니다. 다시 입력해 주세요.";
    private static final String MENU_SEPARATOR = ",";

    private Utils() {
    }

    public static int convertStringToInt(final String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    public static List<String> splitDifferentMenus(final String string) {
        return Arrays.stream(string.split(MENU_SEPARATOR))
                .toList();
    }
}
