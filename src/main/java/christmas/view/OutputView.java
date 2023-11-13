package christmas.view;

import christmas.model.Badge;
import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.GiveawayEvent;
import christmas.model.Money;
import christmas.model.Order;
import christmas.model.Orders;
import java.util.List;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String VISIT_DATE_QUESTION_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_INSTRUCTION_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_PREVIEW_MESSAGE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String ORDER_MENU_FORMAT = "%s %d개\n";
    private static final String PRICE_BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    private static final String PRICE_FORMAT = "%s원\n";
    private static final String DISCOUNT_PRICE_FORMAT = "-%s원\n";
    public static final String TOTAL_BENEFIT_PRICE_HEADER = "<총혜택 금액>";
    private static final String DISCOUNTED_PRICE_HEADER = "<할인 후 예상 결제 금액>";
    private static final String BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String DEFAULT_GIVEAWAY_MENU_HEADER = "<증정 메뉴>";
    private static final String BENEFIT_DETAIL_FORMAT = "%s: -%s원\n";
    private static final String NO_BENEFIT_MESSAGE = "없음";
    private static final List<String> BENEFIT_DETAIL_MESSAGES = List.of("크리스마스 디데이 할인", "평일 할인", "주말 할인", "특별 할인");
    private static final int CHRISTMAS_DAY_DISCOUNT_INDEX = 0;
    private static final int WEEKDAY_DISCOUNT_INDEX = 1;
    private static final int WEEKEND_DISCOUNT_INDEX = 2;
    private static final int SPECIAL_DISCOUNT_INDEX = 3;
    private static final String BENEFIT_HEADER = "<혜택 내역>";
    private static final String GIVEAWAY_EVENT_MESSAGE = "증정 이벤트";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printVisitDateMessage() {
        System.out.println(VISIT_DATE_QUESTION_MESSAGE);
    }

    public void printOrderInstruction() {
        System.out.println(ORDER_INSTRUCTION_MESSAGE);
    }

    public void printOrderDetails(final Day day, final Order order) {
        printEventPreview(day);
        printOrderedMenus(order);
    }

    public void printPriceBeforeDiscount(final Money priceBeforeDiscount) {
        System.out.println(PRICE_BEFORE_DISCOUNT_HEADER);
        System.out.printf(PRICE_FORMAT, priceBeforeDiscount.getFormattedMoney());
        printEmptyLine();
    }

    public void printTotalAndDiscountPrice(final Money totalBenefitPrice, final Orders orders,
                                           final Money discountPrice) {
        printTotalBenefitPrice(totalBenefitPrice);
        printDiscountedPrice(orders.calculateDiscountedPrice(discountPrice));
        printBadge(Badge.decide(totalBenefitPrice));
    }

    public void printExceptionMessage(final Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printGiveawayMenu(final GiveawayEvent giveawayEvent) {
        System.out.println(DEFAULT_GIVEAWAY_MENU_HEADER);
        System.out.println(giveawayEvent.getGiveawayMenu());
        printEmptyLine();
    }

    public void printDiscountBenefitDetails(final List<Money> discountPrices, final Money event) {
        System.out.println(BENEFIT_HEADER);
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(CHRISTMAS_DAY_DISCOUNT_INDEX),
                discountPrices.get(CHRISTMAS_DAY_DISCOUNT_INDEX));
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(WEEKDAY_DISCOUNT_INDEX),
                discountPrices.get(WEEKDAY_DISCOUNT_INDEX));
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(WEEKEND_DISCOUNT_INDEX),
                discountPrices.get(WEEKEND_DISCOUNT_INDEX));
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(SPECIAL_DISCOUNT_INDEX),
                discountPrices.get(SPECIAL_DISCOUNT_INDEX));
        printBenefitDetails(GIVEAWAY_EVENT_MESSAGE, event);
    }

    public void printNoBenefitIfApplicable(final Money discountPrice, final Money giveawayEventMenuPrice) {
        if (discountPrice.equals(Constants.ZERO_WON) && giveawayEventMenuPrice.equals(Constants.ZERO_WON)) {
            System.out.println(NO_BENEFIT_MESSAGE);
        }
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private void printEventPreview(final Day visitedDay) {
        System.out.printf(EVENT_PREVIEW_MESSAGE_FORMAT, visitedDay.getMonth(), visitedDay.getDay());
        printEmptyLine();
    }

    private void printOrderedMenus(final Order order) {
        System.out.println(ORDER_MENU_HEADER);
        order.getOrderedMenus()
                .forEach((menu, quantity) -> System.out.printf(ORDER_MENU_FORMAT, menu.getName(), quantity));
        printEmptyLine();
    }

    private void printTotalBenefitPrice(final Money totalBenefitPrice) {
        System.out.println(TOTAL_BENEFIT_PRICE_HEADER);
        System.out.printf(DISCOUNT_PRICE_FORMAT, totalBenefitPrice.getFormattedMoney());
        printEmptyLine();
    }

    private void printDiscountedPrice(final Money discountedPrice) {
        System.out.println(DISCOUNTED_PRICE_HEADER);
        System.out.printf(PRICE_FORMAT, discountedPrice.getFormattedMoney());
        printEmptyLine();
    }

    private void printBadge(final Badge badge) {
        System.out.println(BADGE_HEADER);
        System.out.println(badge.getName());
    }

    private void printBenefitDetails(final String message, final Money discountPrice) {
        if (!discountPrice.equals(Constants.ZERO_WON)) {
            System.out.printf(BENEFIT_DETAIL_FORMAT, message, discountPrice.getFormattedMoney());
        }
    }
}
