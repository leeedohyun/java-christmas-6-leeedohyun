package christmas.controller;

import christmas.exception.ExceptionHandler;
import christmas.model.Day;
import christmas.model.GiveawayEvent;
import christmas.model.Menu;
import christmas.model.Money;
import christmas.model.Order;
import christmas.model.Orders;
import christmas.model.Utils;
import christmas.model.discount.DiscountManager;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        outputView.printWelcomeMessage();

        final Day day = createValidDate();
        final Order order = createValidOrder();

        outputView.printOrderDetails(day, order);

        final Orders orders = crateOrders(day, order);
        outputView.printDiscountBenefitDetails(orders.getDiscountPrices(), orders.getGiveawayEventMenuPrice());

        final Money discountPrice = orders.calculateTotalDiscountPrice();
        outputView.printNoBenefitIfApplicable(discountPrice, orders.getGiveawayEventMenuPrice());

        final Money totalBenefitPrice = orders.calculateTotalBenefitPrice();
        outputView.printTotalAndDiscountPrice(totalBenefitPrice, orders, discountPrice);
    }

    private Orders crateOrders(final Day day, final Order order) {
        final Money priceBeforeDiscount = order.calculateOrderedPriceBeforeDiscount();
        final GiveawayEvent giveawayEvent = GiveawayEvent.create(priceBeforeDiscount);

        outputView.printPriceBeforeDiscount(priceBeforeDiscount);
        outputView.printGiveawayMenu(giveawayEvent);
        return new Orders(day, order, priceBeforeDiscount, new DiscountManager(), giveawayEvent);
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
