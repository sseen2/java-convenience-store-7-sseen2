package store.domain;

import store.view.enums.ErrorMessage;

import java.util.Map;

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

    public boolean isReorder(Product product, ProductPromotions productPromotions) {
        return productPromotions.getTotalPrizeGift(quantity, product);
    }

    public int orderAgainQuantity(Product product, ProductPromotions productPromotions) {
        return productPromotions.getOrderAgainQuantity(quantity, product);
    }

    public void setTotalPrice(Map<String, Integer> prices, Product product, ProductPromotions productPromotions) {
        productPromotions.setTotalPrice(prices, product, quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice(Product product) {
        return product.getTotalPrice(quantity);
    }

    @Override
    public String toString() {
        return name + " " + quantity;
    }
}
