package christmas.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Utils {

    private static final String DATE_FORMAT_EXCEPTION_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU_SEPARATOR = ",";
    private static final String MENU_AND_QUANTITY_SEPARATOR = "-";

    private Utils() {
    }

    public static LocalDate convertStringToLocalDate(final String day) {
        try {
            return LocalDate.of(Constants.YEAR, Constants.MONTH, convertStringToInt(day));
        } catch (final DateTimeException dateTimeException) {
            throw new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    public static List<String> splitDifferentMenus(final String string) {
        return Arrays.stream(string.split(MENU_SEPARATOR))
                .toList();
    }

    public static Map<Menu, Integer> convertToMenuQuantityMap(final List<String> menus) {
        return menus.stream()
                .map(menu -> Arrays.asList(menu.split(MENU_AND_QUANTITY_SEPARATOR)))
                .collect(Collectors.toMap(
                        menu -> Menu.getMenuByName(menu.get(0)),
                        menu -> convertStringToInt(menu.get(1)))
                );
    }

    private static int convertStringToInt(final String string) {
        try {
            return Integer.parseInt(string);
        } catch (final NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
