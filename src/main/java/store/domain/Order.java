package store.domain;

import store.view.enums.ErrorMessage;

import java.util.Map;

public class Order {
    private static final String TAB = "\t\t";
    private final String name;
    private int quantity;
    private int promotionPrizeQuantity;
    private int price;

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
        promotionPrizeQuantity = productPromotions.setTotalPrice(prices, product, quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(Product product) {
        price = product.getTotalPrice(quantity);
    }

    public boolean isPromotionPrizeEmpty() {
        return promotionPrizeQuantity == 0;
    }

    public String printPromotionPrize() {
        return name + TAB + promotionPrizeQuantity;
    }

    @Override
    public String toString() {
        return name + TAB + quantity + TAB + price;
    }
}
