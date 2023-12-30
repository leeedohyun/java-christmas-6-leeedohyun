package christmas.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import christmas.exception.MenuNotFoundException;
import christmas.model.menu.Menu;

public final class Utils {

    private static final String MENU_SEPARATOR = ",";
    private static final String MENU_AND_QUANTITY_SEPARATOR = "-";

    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;

    private Utils() {
    }

    public static int convertStringToLocalDate(final String inputDate) {
        return convertStringToInt(inputDate).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요."));
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
        } catch (final IndexOutOfBoundsException | IllegalStateException | MenuNotFoundException exception) {
            throw new MenuNotFoundException();
        }
    }

    private static int convertStringToMenuQuantity(final String quantity) {
        return convertStringToInt(quantity).orElseThrow(MenuNotFoundException::new);
    }

    private static List<String> splitMenuAndQuantity(final String menu) {
        return Arrays.asList(menu.split(MENU_AND_QUANTITY_SEPARATOR));
    }

    private static Optional<Integer> convertStringToInt(final String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (final NumberFormatException numberFormatException) {
            return Optional.empty();
        }
    }
}
