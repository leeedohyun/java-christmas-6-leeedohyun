package christmas.model;

public final class Utils {

    private static final String DATE_FORMAT_EXCEPTION_MESSAGE = "[ERROR] 날짜는 숫자만 입력할 수 있습니다. 다시 입력해 주세요.";

    private Utils() {
    }

    public static int convertStringToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(DATE_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
