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

    private static final String GIVEAWAY_EVENT_MENU = "샴페인 1개";
    private static final String NO_BENEFIT_MESSAGE = "없음";

    public void printWelcomeMessage() {
        System.out.println(ViewConstants.WELCOME_MESSAGE);
    }

    public void printOrderDetails(final Date dateOfVisit, final OrderDetail orderDetail) {
        printEventPreview(dateOfVisit);
        printOrderedMenus(orderDetail);
    }

    public void printPriceBeforeDiscount(final Price priceBeforeDiscount) {
        System.out.println(ViewConstants.PRICE_BEFORE_DISCOUNT_HEADER);
        System.out.printf(ViewConstants.PRICE_FORMAT, ViewFormatter.getFormattedMoney(priceBeforeDiscount));
        printEmptyLine();
    }

    public void printTotalAndDiscountPrice(final Price totalBenefitPrice, final Order order,
                                           final Price discountPrice) {
        printTotalBenefitPrice(totalBenefitPrice);
        printDiscountedPrice(order.calculateDiscountedPrice(discountPrice));
        printBadge(Badge.decide(totalBenefitPrice));
    }

    public static void printExceptionMessage(final Exception exception) {
        System.out.println(ViewConstants.ERROR_HEADER + exception.getMessage());
    }

    public void printGiveawayMenu(final GiveawayEvent giveawayEvent) {
        System.out.println(ViewConstants.DEFAULT_GIVEAWAY_MENU_HEADER);
        if (giveawayEvent.canGetGiveawayEventMenu()) {
            System.out.println(GIVEAWAY_EVENT_MENU);
            return;
        }
        System.out.println(NO_BENEFIT_MESSAGE);
        printEmptyLine();
    }

    public void printDiscountBenefitDetails(final List<Price> discountPrices, final Price event) {
        System.out.println(ViewConstants.BENEFIT_HEADER);
        printBenefitDetails(ViewConstants.BENEFIT_DETAIL_MESSAGES.get(ViewConstants.CHRISTMAS_DAY_DISCOUNT_INDEX),
                discountPrices.get(ViewConstants.CHRISTMAS_DAY_DISCOUNT_INDEX));
        printBenefitDetails(ViewConstants.BENEFIT_DETAIL_MESSAGES.get(ViewConstants.WEEKDAY_DISCOUNT_INDEX),
                discountPrices.get(ViewConstants.WEEKDAY_DISCOUNT_INDEX));
        printBenefitDetails(ViewConstants.BENEFIT_DETAIL_MESSAGES.get(ViewConstants.WEEKEND_DISCOUNT_INDEX),
                discountPrices.get(ViewConstants.WEEKEND_DISCOUNT_INDEX));
        printBenefitDetails(ViewConstants.BENEFIT_DETAIL_MESSAGES.get(ViewConstants.SPECIAL_DISCOUNT_INDEX),
                discountPrices.get(ViewConstants.SPECIAL_DISCOUNT_INDEX));
        printBenefitDetails(ViewConstants.GIVEAWAY_EVENT_MESSAGE, event);
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
        System.out.printf(ViewConstants.EVENT_PREVIEW_MESSAGE_FORMAT, dateOfVisit.getMonth(), dateOfVisit.getDate());
        printEmptyLine();
    }

    private void printOrderedMenus(final OrderDetail orderDetail) {
        System.out.println(ViewConstants.ORDER_MENU_HEADER);
        orderDetail.getOrderedMenus()
                .forEach((menu, quantity) ->
                        System.out.printf(ViewConstants.ORDER_MENU_FORMAT, menu.getName(), quantity));
        printEmptyLine();
    }

    private void printTotalBenefitPrice(final Price totalBenefitPrice) {
        System.out.println(ViewConstants.TOTAL_BENEFIT_PRICE_HEADER);
        System.out.printf(ViewConstants.DISCOUNT_PRICE_FORMAT, ViewFormatter.getFormattedMoney(totalBenefitPrice));
        printEmptyLine();
    }

    private void printDiscountedPrice(final Price discountedPrice) {
        System.out.println(ViewConstants.DISCOUNTED_PRICE_HEADER);
        System.out.printf(ViewConstants.PRICE_FORMAT, ViewFormatter.getFormattedMoney(discountedPrice));
        printEmptyLine();
    }

    private void printBadge(final Badge badge) {
        System.out.println(ViewConstants.BADGE_HEADER);
        System.out.println(badge.getName());
    }

    private void printBenefitDetails(final String message, final Price discountPrice) {
        if (!discountPrice.isZero()) {
            System.out.printf(ViewConstants.BENEFIT_DETAIL_FORMAT, message, ViewFormatter.getFormattedMoney(discountPrice));
        }
    }
}
