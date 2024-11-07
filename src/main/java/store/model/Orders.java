package store.model;

import store.model.Order;

import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}
