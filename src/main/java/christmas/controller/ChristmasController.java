package christmas.controller;

import christmas.exception.ExceptionHandler;
import christmas.model.Date;
import christmas.model.GiveawayEvent;
import christmas.model.Menu;
import christmas.model.Price;
import christmas.model.order.OrderDetail;
import christmas.model.order.Order;
import christmas.util.Utils;
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

        final Date date = createValidDate();
        final OrderDetail orderDetail = createValidOrder();

        outputView.printOrderDetails(date, orderDetail);

        final Order order = crateOrders(date, orderDetail);
        final Price discountPrice = order.calculateTotalDiscountPrice();
        final Price totalBenefitPrice = order.calculateTotalBenefitPrice();

        outputView.printDiscountBenefitDetails(order.getDiscountPrices(), order.getGiveawayEventMenuPrice());
        outputView.printNoBenefitIfApplicable(discountPrice, order.getGiveawayEventMenuPrice());
        outputView.printTotalAndDiscountPrice(totalBenefitPrice, order, discountPrice);
    }

    private Order crateOrders(final Date date, final OrderDetail orderDetail) {
        final Price priceBeforeDiscount = orderDetail.calculateOrderedPriceBeforeDiscount();
        final GiveawayEvent giveawayEvent = GiveawayEvent.create(priceBeforeDiscount);

        outputView.printPriceBeforeDiscount(priceBeforeDiscount);
        outputView.printGiveawayMenu(giveawayEvent);
        return new Order(date, orderDetail, priceBeforeDiscount, new DiscountManager(), giveawayEvent);
    }

    private Date createValidDate() {
        return ExceptionHandler.createValidObject(() -> {
            outputView.printVisitDateMessage();
            return new Date(inputView.inputVisitedDate());
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
