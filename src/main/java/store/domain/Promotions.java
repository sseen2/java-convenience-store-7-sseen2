package store.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Promotions {
    private final List<Promotion> promotions;

    public Promotions(List<ConvenienceStore> promotions) {
        this.promotions = castToPromotion(promotions);
    }

    private List<Promotion> castToPromotion(List<ConvenienceStore> promotions) {
        List<Promotion> castToPromotion = new ArrayList<>();
        for (ConvenienceStore promotion : promotions) {
            castToPromotion.add((Promotion) promotion);
        }
        return castToPromotion;
    }

    public void returnPromotionProducts(Map<Product, Promotion> productPromotions, List<Product> products) {
        for (Product product : products) {
            compareNamePromotionsAndProduct(productPromotions, product);
        }
    }

    private void compareNamePromotionsAndProduct(Map<Product, Promotion> productPromotions, Product product) {
        for (Promotion promotion : promotions) {
            if (promotion.compareName(product)) {
                productPromotions.put(product, promotion);
                return;
            }
        }
        product.setPromotion("null");
        product.setQuantity();
    }
}
