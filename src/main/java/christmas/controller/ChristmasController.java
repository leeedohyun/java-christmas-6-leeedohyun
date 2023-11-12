package christmas.controller;

import christmas.model.Badge;
import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.Discount;
import christmas.model.GiveawayEvent;
import christmas.model.Menu;
import christmas.model.Money;
import christmas.model.Order;
import christmas.model.Utils;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        outputView.printWelcomeMessage();
        while (true) {
            try {
                Day day = createValidDate();

                Order order = createValidOrder();

                outputView.printEventPreview(day);
                outputView.printOrderedMenus(order);

                Money total = order.calculateOrderedPriceBeforeDiscount();
                outputView.printPriceBeforeDiscount(total);

                System.out.println("<증정 메뉴>");
                GiveawayEvent giveawayEvent = GiveawayEvent.create(total);
                System.out.println(giveawayEvent.getGiveawayMenu());

                Discount discount = new Discount();

                Money dDay = discount.discountDDay(day, total);
                Money weekDay = discount.discountWeekDay(day, total, order.countDessert());
                Money weekend = discount.discountWeekend(day, total, order.countMainMenu());
                Money special = discount.discountSpecial(day, total);

                System.out.println();
                System.out.println("<혜택 내역>");
                Money event = giveawayEvent.getGiveawayMenuPrice();
                if (!dDay.equals(Constants.ZERO_WON)) {
                    System.out.printf("크리스마스 디데이 할인: -%s원\n", dDay.getFormattedMoney());
                }
                if (!weekDay.equals(Constants.ZERO_WON)) {
                    System.out.printf("평일 할인: -%s원\n", weekDay.getFormattedMoney());
                }
                if (!weekend.equals(Constants.ZERO_WON)) {
                    System.out.printf("주말 할인: -%s원\n", weekend.getFormattedMoney());
                }
                if (!special.equals(Constants.ZERO_WON)) {
                    System.out.printf("특별 할인: -%s원\n", special.getFormattedMoney());
                }
                if (giveawayEvent.isEventActive()) {
                    System.out.printf("증정 이벤트: -%s원\n", event.getFormattedMoney());
                    System.out.println();
                }
                if (dDay.equals(Constants.ZERO_WON) && weekDay.equals(Constants.ZERO_WON) && weekend.equals(
                        Constants.ZERO_WON)
                        && special.equals(Constants.ZERO_WON) && !giveawayEvent.isEventActive()) {
                    System.out.println("없음\n");
                }

                System.out.println("<총혜택 금액>");
                Money discountPrice = dDay.plus(weekDay.plus(weekend.plus(special.plus(event))));
                System.out.printf("-%s원\n", discountPrice.getFormattedMoney());
                System.out.println();

                System.out.println("<할인 후 예상 결제 금액>");
                System.out.printf("%s원\n", total.plus(discountPrice.multiply(-1).plus(event)).getFormattedMoney());
                System.out.println();

                System.out.println("<12월 이벤트 배지>");
                Badge badge = Badge.decide(discountPrice);
                System.out.println(badge.getName());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Day createValidDate() {
        try {
            outputView.printVisitDateMessage();
            return new Day(inputView.inputVisitedDate());
        } catch (final IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return createValidDate();
        }
    }

    private Map<Menu, Integer> createValidMenus() {
        try {
            outputView.printOrderInstruction();
            List<String> strings = inputView.inputMenusWithQuantity();
            return Utils.convertToMenuQuantityMap(strings);
        } catch (IllegalStateException illegalStateException) {
            throw new IllegalStateException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    private Order createValidOrder() {
        try {
            return new Order(createValidMenus());
        } catch (final IllegalArgumentException | IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return createValidOrder();
        }
    }

    private void validateDuplicateMenu(List<String> menuAndQuantity, Map<Menu, Integer> orderedMenus) {
        System.out.println("menuAndQuantity = " + menuAndQuantity.size());
        System.out.println("orderedMenus = " + orderedMenus.size());
        if (menuAndQuantity.size() != orderedMenus.size()) {
            throw new IllegalArgumentException(Constants.MENU_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}
