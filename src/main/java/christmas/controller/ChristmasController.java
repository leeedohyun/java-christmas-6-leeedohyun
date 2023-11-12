package christmas.controller;

import christmas.exception.ExceptionHandler;
import christmas.model.Badge;
import christmas.model.Constants;
import christmas.model.Day;
import christmas.model.GiveawayEvent;
import christmas.model.Menu;
import christmas.model.Money;
import christmas.model.Order;
import christmas.model.Utils;
import christmas.model.discount.DDay;
import christmas.model.discount.Discount;
import christmas.model.discount.Special;
import christmas.model.discount.WeekDay;
import christmas.model.discount.Weekend;
import christmas.view.InputView;
import christmas.view.OutputView;
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
        Day day = createValidDate();

        Order order = createValidOrder();

        outputView.printEventPreview(day);
        outputView.printOrderedMenus(order);

        Money total = order.calculateOrderedPriceBeforeDiscount();
        outputView.printPriceBeforeDiscount(total);

        GiveawayEvent giveawayEvent = GiveawayEvent.create(total);
        outputView.printGiveawayMenu(giveawayEvent);

        Discount dDayDiscount = new DDay();
        Discount weekDayDiscount = new WeekDay();
        Discount weekendDiscount = new Weekend();
        Discount specialDiscount = new Special();

        Money dDay = dDayDiscount.discount(day, total, 0);
        Money weekDay = weekDayDiscount.discount(day, total, order.countDessert());
        Money weekend = weekendDiscount.discount(day, total, order.countMainMenu());
        Money special = specialDiscount.discount(day, total, 0);

        System.out.println("<혜택 내역>");
        Money event = giveawayEvent.getGiveawayMenuPrice();

        outputView.printBenefitDetails("크리스마스 디데이 할인", dDay);
        outputView.printBenefitDetails("평일 할인", weekDay);
        outputView.printBenefitDetails("주말 할인", weekend);
        outputView.printBenefitDetails("특별 할인", special);
        outputView.printBenefitDetails("증정 이벤트", event);

        if (dDay.equals(Constants.ZERO_WON) && weekDay.equals(Constants.ZERO_WON) && weekend.equals(
                Constants.ZERO_WON)
                && special.equals(Constants.ZERO_WON) && !giveawayEvent.isEventActive()) {
            System.out.println("없음\n");
        }

        System.out.println();
        Money discountPrice = dDay.plus(weekDay.plus(weekend.plus(special.plus(event))));
        outputView.printTotalBenefitPrice(discountPrice);

        outputView.printDiscountedPrice(total.plus(discountPrice.multiply(-1).plus(event)));

        Badge badge = Badge.decide(discountPrice);
        outputView.printBadge(badge);

    }

    private Day createValidDate() {
        return ExceptionHandler.createValidObject(() -> {
            outputView.printVisitDateMessage();
            return new Day(inputView.inputVisitedDate());
        }, outputView::printExceptionMessage);
    }

    private Map<Menu, Integer> createValidMenus() {
        return ExceptionHandler.createValidObject(() -> {
            outputView.printOrderInstruction();
            return Utils.convertToMenuQuantityMap(inputView.inputMenusWithQuantity());
        }, outputView::printExceptionMessage);
    }

    private Order createValidOrder() {
        return ExceptionHandler.createValidObject(() -> new Order(createValidMenus()),
                outputView::printExceptionMessage);
    }
}
