package store.domain;

public class Product implements ConvenienceStore {
    private final String name;
    private final int price;
    private int promotionQuantity;
    private int generalQuantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        setQuantity(quantity);
    }

    private void setQuantity(int quantity) {
        if (isPromotionNull()) {
            this.generalQuantity = quantity;
        }
        if (!isPromotionNull()) {
            this.promotionQuantity = quantity;
        }
    }
    public void setPromotionQuantity(int quantity) {
        this.promotionQuantity = quantity;
    }

    public void setGeneralQuantity(int quantity) {
        this.generalQuantity = quantity;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public boolean isNameEquals(String name) {
        return this.name.equals(name);
    }

    public boolean isEnoughQuantity(int quantity) {
        return (promotionQuantity + generalQuantity) >= quantity;
    }

    public boolean isPromotionEquals(String name) {
        return this.promotion.equals(name);
    }

    public boolean isPromotionNull() {
        return this.promotion.equals("null");
    }

    private String generalQuantityString() {
        if (generalQuantity == 0) {
            return " 재고 없음";
        }

        return " " + generalQuantity + "개";
    }

    private String promotionQuantityString() {
        if (promotionQuantity == 0) {
            return " 재고 없음";
        }

        return " " + promotionQuantity + "개";
    }

    private String promotionString() {
        return String.format("- %s %,d원", name, price) + promotionQuantityString() + " " + promotion;
    }

    private String generalString() {
        return String.format("- %s %,d원", name, price) + generalQuantityString();
    }

    @Override
    public String toString() {
        if (isPromotionNull()) {
            return generalString();
        }
        return promotionString() + "\n" + generalString();
    }
}
