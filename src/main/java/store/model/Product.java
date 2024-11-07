package store.model;

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

    @Override
    public String toString() {
        return "- " + name + String.format(" %,d원", price) + quantityString() + promotionString();
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
}
