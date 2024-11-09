package store.domain;

import java.util.List;

public class Order {
    private final String name;
    private final int quantity;

    public Order(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public boolean isNameEquals(Product product) {
        return product.isNameEquals(this.name);
    }
}
