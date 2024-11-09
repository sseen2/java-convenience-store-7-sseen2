package store.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProducts {
    private final Map<Order, Product> orderProducts;

    public OrderProducts(Orders orders, Products products) {
        this.orderProducts = createOrderProducts(orders, products);
    }

    private Map<Order, Product> createOrderProducts(Orders orders, Products products) {
        Map<Order, Product> orderProducts = new HashMap<>();

        List<Product> promotionProducts = products.returnPromotionProducts();
        orderProducts.putAll(orders.putProducts(promotionProducts));

        List<Product> notPromotionProducts = products.returnNotPromotionProducts();
        orderProducts.putAll(orders.putProducts(notPromotionProducts));

        return orderProducts;
    }
}
