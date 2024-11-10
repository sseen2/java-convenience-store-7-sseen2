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

    public boolean getTotalPrizeGift(int quantity, Product product) {
        Promotion promotion = productPromotions.get(product);
        return isProductInPromotion(product) &&
                promotion.isPromotionApplicable(quantity) &&
                product.isEnoughPromotionQuantity(quantity);
    }

    public int getOrderAgainQuantity(int quantity, Product product) {
        Promotion promotion = productPromotions.get(product);
        int underStock = product.promotionQuantityUnderStock(promotion);
        if (isProductInPromotion(product) &&
                !product.isEnoughPromotionQuantity(quantity)) {
            return quantity + underStock;
        }
        return -1;
    }

    private boolean isProductInPromotion(Product product) {
        return productPromotions.containsKey(product);
    }
}
