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

    void setQuantity(int quantity) {
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

    public void setQuantity() {
        this.generalQuantity += this.promotionQuantity;
        this.promotionQuantity = 0;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public void updateQuantity(int quantity) {
        this.promotionQuantity -= quantity;
        if (promotionQuantity <= 0) {
            promotion = "null";
            generalQuantity += promotionQuantity;
            promotionQuantity = 0;
        }
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public Integer getTotalPrice(int quantity) {
        return quantity * price;
    }

    public boolean isNameEquals(String name) {
        return this.name.equals(name);
    }

    public boolean isEnoughQuantity(int quantity) {
        return (promotionQuantity + generalQuantity) >= quantity;
    }

    public boolean isEnoughPromotionQuantity(int quantity) {
        return promotionQuantity >= quantity;
    }

    public boolean isPromotionEquals(String name) {
        return this.promotion.equals(name);
    }

    public boolean isPromotionNull() {
        return this.promotion.equals("null");
    }

    public int promotionQuantityUnderStock(Promotion promotion) {
        if (promotionQuantity == 0) {
            return -1;
        }
        return promotion.underStock(promotionQuantity) - promotionQuantity;
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
