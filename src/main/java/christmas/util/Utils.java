package christmas.util;

import christmas.model.Constants;
import christmas.model.menu.Menu;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Utils {

    private static final String DATE_FORMAT_EXCEPTION_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU_SEPARATOR = ",";
    private static final String MENU_AND_QUANTITY_SEPARATOR = "-";

    private Utils() {
    }

    public static LocalDate convertStringToLocalDate(final String inputDate) {
        final int date = convertStringToInt(inputDate)
                .orElseThrow(() -> new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE));
        return createLocalDate(date);
    }
    
    public static List<String> splitDifferentMenus(final String string) {
        return Arrays.stream(string.split(MENU_SEPARATOR))
                .toList();
    }

    public static Map<Menu, Integer> convertToMenuQuantityMap(final List<String> menus) {
        try {
            return menus.stream()
                    .map(menu -> Arrays.asList(menu.split(MENU_AND_QUANTITY_SEPARATOR)))
                    .collect(Collectors.toMap(
                            menu -> Menu.getMenuByName(menu.get(0)),
                            menu -> convertStringToMenuQuantity(menu.get(1)))
                    );
        } catch (final IndexOutOfBoundsException | IllegalStateException exception) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    private static Optional<Integer> convertStringToInt(final String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (final NumberFormatException numberFormatException) {
            return Optional.empty();
        }
    }

    private static LocalDate createLocalDate(final int date) {
        try {
            return LocalDate.of(Constants.YEAR, Constants.MONTH, date);
        } catch (final DateTimeException dateTimeException) {
            throw new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private static int convertStringToMenuQuantity(final String quantity) {
        return convertStringToInt(quantity)
                .orElseThrow(() -> new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}
