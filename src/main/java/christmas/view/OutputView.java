package christmas.view;

import christmas.model.Day;
import christmas.model.Money;
import christmas.model.Order;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String VISIT_DATE_QUESTION_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_INSTRUCTION_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_PREVIEW_MESSAGE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String PRICE_BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    private static final String PRICE_FORMAT = "%s원";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printVisitDateMessage() {
        System.out.println(VISIT_DATE_QUESTION_MESSAGE);
    }

    public void printOrderInstruction() {
        System.out.println(ORDER_INSTRUCTION_MESSAGE);
    }

    public void printEventPreview(Day visitedDay) {
        System.out.printf(EVENT_PREVIEW_MESSAGE_FORMAT, visitedDay.getMonth(), visitedDay.getDay());
        printEmptyLine();
        printEmptyLine();
    }

    public void printOrderedMenus(Order order) {
        System.out.println(ORDER_MENU_HEADER);
        order.getOrderedMenus()
                .forEach((menu, quantity) -> System.out.printf(ORDER_MENU_FORMAT, menu.getName(), quantity));
        printEmptyLine();
        printEmptyLine();
    }

    public void printPriceBeforeDiscount(Money priceBeforeDiscount) {
        System.out.println(PRICE_BEFORE_DISCOUNT_HEADER);
        System.out.printf(PRICE_FORMAT, priceBeforeDiscount.getFormattedMoney());
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
