package store.domain;

import store.DateParser;

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

    public boolean isPromotionApplicable(int quantity) {
        int promotionApplicable = quantity % (buy + get);
        return promotionApplicable != 0 && promotionApplicable % buy == 0;
    }
}
