package christmas.model.order;

import christmas.model.Date;
import christmas.model.Price;

public class OrderHistory {

    private final Date date;
    private final OrderDetail orderDetail;

    public OrderHistory(final Date date, final OrderDetail orderDetail) {
        this.date = date;
        this.orderDetail = orderDetail;
    }

    public Price getPriceBeforeDiscount() {
        return orderDetail.calculateOrderedPriceBeforeDiscount();
    }

    public Date getDate() {
        return date;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "date=" + date +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
