package christmas.view;

import christmas.model.Badge;
import christmas.model.Constants;
import christmas.model.Date;
import christmas.model.GiveawayEvent;
import christmas.model.Price;
import christmas.model.order.OrderDetail;
import christmas.model.order.Order;
import java.util.List;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(ViewConstants.WELCOME_MESSAGE);
    }

    public void printVisitDateMessage() {
        System.out.println(ViewConstants.VISIT_DATE_QUESTION_MESSAGE);
    }

    public void printOrderInstruction() {
        System.out.println(ViewConstants.ORDER_INSTRUCTION_MESSAGE);
    }

    public void printOrderDetails(final Date dateOfVisit, final OrderDetail orderDetail) {
        printEventPreview(dateOfVisit);
        printOrderedMenus(orderDetail);
    }

    public void printPriceBeforeDiscount(final Price priceBeforeDiscount) {
        System.out.println(ViewConstants.PRICE_BEFORE_DISCOUNT_HEADER);
        System.out.printf(ViewConstants.PRICE_FORMAT, priceBeforeDiscount.getFormattedMoney());
        printEmptyLine();
    }

    public void printTotalAndDiscountPrice(final Price totalBenefitPrice, final Order order,
                                           final Price discountPrice) {
        printTotalBenefitPrice(totalBenefitPrice);
        printDiscountedPrice(order.calculateDiscountedPrice(discountPrice));
        printBadge(Badge.decide(totalBenefitPrice));
    }

    public void printExceptionMessage(final Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printGiveawayMenu(final GiveawayEvent giveawayEvent) {
        System.out.println(ViewConstants.DEFAULT_GIVEAWAY_MENU_HEADER);
        System.out.println(giveawayEvent.getGiveawayMenu());
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

    public void printNoBenefitIfApplicable(final Price discountPrice, final Price giveawayEventMenuPrice) {
        if (discountPrice.equals(Constants.ZERO_WON) && giveawayEventMenuPrice.equals(Constants.ZERO_WON)) {
            System.out.println(Constants.NO_BENEFIT_MESSAGE);
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
        System.out.printf(ViewConstants.DISCOUNT_PRICE_FORMAT, totalBenefitPrice.getFormattedMoney());
        printEmptyLine();
    }

    private void printDiscountedPrice(final Price discountedPrice) {
        System.out.println(ViewConstants.DISCOUNTED_PRICE_HEADER);
        System.out.printf(ViewConstants.PRICE_FORMAT, discountedPrice.getFormattedMoney());
        printEmptyLine();
    }

    private void printBadge(final Badge badge) {
        System.out.println(ViewConstants.BADGE_HEADER);
        System.out.println(badge.getName());
    }

    private void printBenefitDetails(final String message, final Price discountPrice) {
        if (!discountPrice.equals(Constants.ZERO_WON)) {
            System.out.printf(ViewConstants.BENEFIT_DETAIL_FORMAT, message, discountPrice.getFormattedMoney());
        }
    }
}
