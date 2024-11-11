package store.view.enums;

public enum PriceType {
    PROMOTION_PRICE("promotionPrice"),
    PROMOTION_PRIZE_PRICE("promotionPrizePrice"),
    GENERAL_PRICE("generalPrice");

    private final String price;

    PriceType(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
}
