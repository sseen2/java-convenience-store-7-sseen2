package store.domain;

import java.time.LocalDate;

public class Promotion implements ConvenienceStore {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean compareName(Product product) {
        return product.isPromotionEquals(this.name);
    }

    public int underStock(int quantity) {
        return quantity % getTotalQuantity();
    }

    private int getTotalQuantity() {
        return buy + get;
    }

    public int getPromotionPrize(int quantity) {
        return quantity / getTotalQuantity();
    }

    public int getPromotionQuantity(int promotionQuantity) {
        return promotionQuantity * buy;
    }

    public boolean isPromotionApplicable(int quantity) {
        int promotionApplicable = quantity % getTotalQuantity();
        return promotionApplicable != 0 && promotionApplicable % buy == 0;
    }
}
