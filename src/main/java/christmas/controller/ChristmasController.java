package christmas.controller;

import christmas.exception.ExceptionHandler;
import christmas.model.Day;
import christmas.model.GiveawayEvent;
import christmas.model.Menu;
import christmas.model.Money;
import christmas.model.OrderDetail;
import christmas.model.Order;
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
        final OrderDetail orderDetail = createValidOrder();

        outputView.printOrderDetails(day, orderDetail);

        final Order order = crateOrders(day, orderDetail);
        outputView.printDiscountBenefitDetails(order.getDiscountPrices(), order.getGiveawayEventMenuPrice());

        final Money discountPrice = order.calculateTotalDiscountPrice();
        outputView.printNoBenefitIfApplicable(discountPrice, order.getGiveawayEventMenuPrice());

        final Money totalBenefitPrice = order.calculateTotalBenefitPrice();
        outputView.printTotalAndDiscountPrice(totalBenefitPrice, order, discountPrice);
    }

    private Order crateOrders(final Day day, final OrderDetail orderDetail) {
        final Money priceBeforeDiscount = orderDetail.calculateOrderedPriceBeforeDiscount();
        final GiveawayEvent giveawayEvent = GiveawayEvent.create(priceBeforeDiscount);

        outputView.printPriceBeforeDiscount(priceBeforeDiscount);
        outputView.printGiveawayMenu(giveawayEvent);
        return new Order(day, orderDetail, priceBeforeDiscount, new DiscountManager(), giveawayEvent);
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

    private OrderDetail createValidOrder() {
        return ExceptionHandler.createValidObject(() -> new OrderDetail(createValidMenus()),
                outputView::printExceptionMessage);
    }
}
