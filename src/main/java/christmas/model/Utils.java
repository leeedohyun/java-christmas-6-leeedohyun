package christmas.model;

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

    public static Map<Menu, Integer> convertToMenuQuantityMap(List<String> menus) {
        return menus.stream()
                .map(menu -> Arrays.asList(menu.split(MENU_AND_QUANTITY_SEPARATOR)))
                .collect(Collectors.toMap(
                        menu -> Menu.getMenuByName(menu.get(0)),
                        menu -> convertStringToInt(menu.get(1)))
                );
    }
}
