package store.domain;

import store.view.enums.ErrorMessage;

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
            int beforePutSize = orderProducts.size();
            compareOrderAndProducts(orderProducts, product);
        }
        return orderProducts;
    }

    private void compareOrderAndProducts(Map<Order, Product> orderProducts, Product product) {
        for (Order order : orders) {
            if (order.isNameEquals(product) && order.isEnoughQuantity(product)) {
                orderProducts.put(order, product);
            }
        }
    }

//    private boolean compareMapSize(int beforePutSize, int afterPutSize) {
//        if (beforePutSize == afterPutSize) {
//            throw new IllegalArgumentException(ErrorMessage.NONEXISTENT_PRODUCT_INPUT.getMessage());
//        }
//        return true;
//    }
}
