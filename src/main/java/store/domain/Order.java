package store.domain;

import store.view.enums.ErrorMessage;

public class Order {
    private final String name;
    private int quantity;

    public Order(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNameEquals(Product product) {
        if (!product.isNameEquals(this.name)) {
            return false;
        }
        return true;
    }

    public boolean isEnoughQuantity(Product product) {
        if (!product.isEnoughQuantity(this.quantity)) {
            throw new IllegalArgumentException(ErrorMessage.INVENTORY_QUANTITY_EXCEEDED_INPUT.getMessage());
        }
        return true;
    }

    @Override
    public String toString() {
        return name + " " + quantity;
    }

    public boolean isReorder(Product product, ProductPromotions productPromotions) {
        return productPromotions.getTotalPrizeGift(quantity, product);
    }
}
