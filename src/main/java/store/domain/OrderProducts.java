package store.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class OrderProducts {
    private final Map<Order, Product> orderProducts;

    public OrderProducts(Orders orders, Products products) {
        this.orderProducts = createOrderProducts(orders, products);
    }

    private Map<Order, Product> createOrderProducts(Orders orders, Products products) {
        Map<Order, Product> orderProducts = new LinkedHashMap<>();
        orderProducts.putAll(orders.putProducts(products.getProducts()));
        return orderProducts;
    }

    public List<Order> createReorders(ProductPromotions productPromotions) {
        List<Order> reorders = new ArrayList<>();
        orderProducts.forEach((order, product) -> {
            if (order.isReorder(product, productPromotions)) {
                reorders.add(order);
            }
        });
        return reorders;
    }

    public Map<Order, Integer> createOrderAgainQuantity(ProductPromotions productPromotions) {
        Map<Order, Integer> orderAgainQuantity = new LinkedHashMap<>();
        orderProducts.forEach((order, product) -> {
            int updateQuantity = order.orderAgainQuantity(product, productPromotions);
            if (updateQuantity > 0) {
                orderAgainQuantity.put(order, updateQuantity);
            }
        });
        return orderAgainQuantity;
    }
}
