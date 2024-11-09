package store.domain;

import store.view.ErrorMessage;

import java.util.List;

public class Order {
    private final String name;
    private final int quantity;

    public Order(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public boolean isNameEquals(Product product) {
        if (!product.isNameEquals(this.name)) {
            throw new IllegalArgumentException(ErrorMessage.NONEXISTENT_PRODUCT_INPUT.getMessage());
        }
        return true;
    }
}
