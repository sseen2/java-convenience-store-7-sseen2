package store.domain;

public class Product implements ConvenienceStore {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public Product(Product product) {
        this.name = product.name;
        this.price = product.price;
        this.quantity = 0;
        this.promotion = "null";
    }

    public boolean isNameEquals(Product product) {
        return this.name.equals(product.name);
    }

    public boolean isNameEquals(String name) {
        return this.name.equals(name);
    }

    public boolean isPromotionEquals(String name) {
        return this.promotion.equals(name);
    }

    public boolean isPromotionNull() {
        return this.promotion.equals("null");
    }

    private String quantityString() {
        if (quantity == 0) {
            return " 재고 없음";
        }

        return " " + quantity + "개";
    }

    private String promotionString() {
        if (promotion.equals("null")) {
            return "";
        }

        return " " + promotion;
    }

    @Override
    public String toString() {
        return "- " + name + String.format(" %,d원", price) + quantityString() + promotionString();
    }
}
