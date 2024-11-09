package store.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPromotions {
    private final Map<Product, Promotion> productPromotions;

    public ProductPromotions(Promotions promotions, Products products) {
        this.productPromotions = createProductPromotions(promotions, products);
    }

    private Map<Product, Promotion> createProductPromotions(Promotions promotions, Products products) {
        Map<Product, Promotion> productPromotions = new HashMap<>();
        List<Product> promotionProductCollection = products.returnPromotionProducts();
        promotions.returnPromotionProducts(productPromotions, promotionProductCollection);
        return productPromotions;
    }
}
