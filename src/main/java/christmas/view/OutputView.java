package christmas.view;

import java.util.List;

import christmas.model.Badge;
import christmas.model.Date;
import christmas.model.GiveawayEvent;
import christmas.model.Price;
import christmas.model.order.Order;
import christmas.model.order.OrderDetail;
import christmas.util.ViewFormatter;

public class OutputView {

    private static final String NO_BENEFIT_MESSAGE = "없음";
    private static final List<String> BENEFIT_DETAIL_MESSAGES = List.of("크리스마스 디데이 할인", "평일 할인", "주말 할인", "특별 할인");

    private static final int CHRISTMAS_DAY_DISCOUNT_INDEX = 0;
    private static final int WEEKDAY_DISCOUNT_INDEX = 1;
    private static final int WEEKEND_DISCOUNT_INDEX = 2;
    private static final int SPECIAL_DISCOUNT_INDEX = 3;

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOrderDetails(final Date dateOfVisit, final OrderDetail orderDetail) {
        printEventPreview(dateOfVisit);
        printOrderedMenus(orderDetail);
    }

    public void printPriceBeforeDiscount(final Price priceBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%s원\n", ViewFormatter.getFormattedMoney(priceBeforeDiscount));
        printEmptyLine();
    }

    public void printTotalAndDiscountPrice(final Price totalBenefitPrice, final Order order,
                                           final Price discountPrice) {
        printTotalBenefitPrice(totalBenefitPrice);
        printDiscountedPrice(order.calculateDiscountedPrice(discountPrice));
        printBadge(Badge.decide(totalBenefitPrice));
    }

    public static void printExceptionMessage(final Exception exception) {
        System.out.println("[ERROR] " + exception.getMessage());
    }

    public void printGiveawayMenu(final GiveawayEvent giveawayEvent) {
        System.out.println("<증정 메뉴>");
        if (giveawayEvent.canGetGiveawayEventMenu()) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println(NO_BENEFIT_MESSAGE);
        printEmptyLine();
    }

    public void printDiscountBenefitDetails(final List<Price> discountPrices, final Price event) {
        System.out.println("<혜택 내역>");
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(CHRISTMAS_DAY_DISCOUNT_INDEX),
                discountPrices.get(CHRISTMAS_DAY_DISCOUNT_INDEX));
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(WEEKDAY_DISCOUNT_INDEX),
                discountPrices.get(WEEKDAY_DISCOUNT_INDEX));
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(WEEKEND_DISCOUNT_INDEX),
                discountPrices.get(WEEKEND_DISCOUNT_INDEX));
        printBenefitDetails(BENEFIT_DETAIL_MESSAGES.get(SPECIAL_DISCOUNT_INDEX),
                discountPrices.get(SPECIAL_DISCOUNT_INDEX));
        printBenefitDetails("증정 이벤트", event);
    }

    public void printNoBenefitIfApplicable(final Price totalBenefitPrice) {
        if (totalBenefitPrice.isZero()) {
            System.out.println(NO_BENEFIT_MESSAGE);
        }
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private void printEventPreview(final Date dateOfVisit) {
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", dateOfVisit.getMonth(), dateOfVisit.getDate());
        printEmptyLine();
    }

    private void printOrderedMenus(final OrderDetail orderDetail) {
        System.out.println("<주문 메뉴>");
        orderDetail.getOrderedMenus()
                .forEach((menu, quantity) ->
                        System.out.printf("%s %d개\n", menu.getName(), quantity));
        printEmptyLine();
    }

    private void printTotalBenefitPrice(final Price totalBenefitPrice) {
        System.out.println("<총혜택 금액>");
        System.out.printf("-%s원\n", ViewFormatter.getFormattedMoney(totalBenefitPrice));
        printEmptyLine();
    }

    private void printDiscountedPrice(final Price discountedPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%s원\n", ViewFormatter.getFormattedMoney(discountedPrice));
        printEmptyLine();
    }

    private void printBadge(final Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getName());
    }

    private void printBenefitDetails(final String message, final Price discountPrice) {
        if (!discountPrice.isZero()) {
            System.out.printf("%s: -%s원\n", message, ViewFormatter.getFormattedMoney(discountPrice));
        }
    }
}
