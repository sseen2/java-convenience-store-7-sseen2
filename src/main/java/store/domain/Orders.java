package store.domain;

import store.view.enums.ErrorMessage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public Map<Order, Product> putProducts(List<Product> products) {
        Map<Order, Product> orderProducts = new LinkedHashMap<>();
        for (Product product : products) {
            compareOrderAndProducts(orderProducts, product);
        }
        return orderProducts;
    }

    private void compareOrderAndProducts(Map<Order, Product> orderProducts, Product product) {
        boolean isOrderProductsPut = false;
        for (Order order : orders) {
            if (order.isNameEquals(product) && order.isEnoughQuantity(product)) {
                orderProducts.put(order, product);
                isOrderProductsPut = true;
            }
        }
        validatePresenceProduct(isOrderProductsPut);
    }

    private void validatePresenceProduct(boolean isOrderProductsPut) {
        if (!isOrderProductsPut) {
            throw new IllegalArgumentException(ErrorMessage.NONEXISTENT_PRODUCT_INPUT.getMessage());
        }
    }
}
