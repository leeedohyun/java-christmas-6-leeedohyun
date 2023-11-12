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
import christmas.model.discount.DiscountManager;
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
        Day day = createValidDate();

        Order order = createValidOrder();

        outputView.printEventPreview(day);
        outputView.printOrderedMenus(order);

        Money total = order.calculateOrderedPriceBeforeDiscount();
        outputView.printPriceBeforeDiscount(total);

        GiveawayEvent giveawayEvent = GiveawayEvent.create(total);
        outputView.printGiveawayMenu(giveawayEvent);

        DiscountManager discountManager = new DiscountManager();

        List<Money> discountPrices = discountManager.calculateTotalDiscountPrice(day, total, order);
        Money discountPrice = discountPrices.stream()
                .reduce(Money::plus)
                .orElse(Constants.ZERO_WON);

        Money event = giveawayEvent.getGiveawayMenuPrice();

        System.out.println("<혜택 내역>");
        outputView.printBenefitDetails("크리스마스 디데이 할인", discountPrices.get(0));
        outputView.printBenefitDetails("평일 할인", discountPrices.get(1));
        outputView.printBenefitDetails("주말 할인", discountPrices.get(2));
        outputView.printBenefitDetails("특별 할인", discountPrices.get(3));
        outputView.printBenefitDetails("증정 이벤트", event);

        if (discountPrice.equals(Constants.ZERO_WON) && !giveawayEvent.isEventActive()) {
            System.out.println("없음");
        }

        System.out.println();
        Money totalBenefitPrice = discountPrice.plus(event);
        outputView.printTotalBenefitPrice(totalBenefitPrice);

        outputView.printDiscountedPrice(Order.calculateDiscountedPrice(total, totalBenefitPrice));

        outputView.printBadge(Badge.decide(totalBenefitPrice));

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
