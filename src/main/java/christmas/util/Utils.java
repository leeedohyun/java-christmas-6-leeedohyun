package christmas.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import christmas.model.Constants;
import christmas.model.menu.Menu;

public final class Utils {

    private static final String DATE_FORMAT_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String MENU_SEPARATOR = ",";
    private static final String MENU_AND_QUANTITY_SEPARATOR = "-";

    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;

    private Utils() {
    }

//    public static LocalDate convertStringToLocalDate(final String inputDate) {
//        final int date = StringToIntConvertor.convert(inputDate)
//                .orElseThrow(() -> new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE));
//        return createLocalDate(date);
//    }
    public static int convertStringToLocalDate(final String inputDate) {
        return StringToIntConvertor.convert(inputDate)
                .orElseThrow(() -> new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE));
    }
    
    public static List<String> splitDifferentMenus(final String string) {
        return Arrays.stream(string.split(MENU_SEPARATOR))
                .toList();
    }

    public static Map<Menu, Integer> convertToMenuQuantityMap(final List<String> menus) {
        try {
            return menus.stream()
                    .map(Utils::splitMenuAndQuantity)
                    .collect(Collectors.toMap(
                            menu -> Menu.getMenuByName(menu.get(MENU_NAME_INDEX)),
                            menu -> convertStringToMenuQuantity(menu.get(MENU_QUANTITY_INDEX)))
                    );
        } catch (final IndexOutOfBoundsException | IllegalStateException exception) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

//    private static LocalDate createLocalDate(final int date) {
//        try {
//            return LocalDate.of(Constants.YEAR, Constants.MONTH, date);
//        } catch (final DateTimeException dateTimeException) {
//            throw new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE);
//        }
//    }

    private static int convertStringToMenuQuantity(final String quantity) {
        return StringToIntConvertor.convert(quantity)
                .orElseThrow(() -> new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE));
    }
    private static List<String> splitMenuAndQuantity(final String menu) {
        return Arrays.asList(menu.split(MENU_AND_QUANTITY_SEPARATOR));
    }
}
