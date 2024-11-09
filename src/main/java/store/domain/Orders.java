package store.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public Map<Order, Product> putProducts(List<Product> products) {
        Map<Order, Product> orderProducts = new HashMap<>();
        for (Product product : products) {
            compareNameOrderAndProducts(orderProducts, product);
        }
        return orderProducts;
    }

    private void compareNameOrderAndProducts(Map<Order, Product> orderProducts, Product product) {
        for (Order order : orders) {
            if (order.isNameEquals(product)) {
                orderProducts.put(order, product);
            }
        }
    }
}
