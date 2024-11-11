package store.domain;

import store.view.enums.PriceType;

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

    public void setTotalPrice(Map<String, Integer> prices, Product product, int quantity) {
        Promotion promotion = productPromotions.get(product);
        int promotionPrizeQuantity = getPromotionPrizeQuantity(product, promotion, quantity);

        int promotionPrizePrice = product.getTotalPrice(promotionPrizeQuantity);
        int promotionPrice = product.getTotalPrice(getPromotionQuantity(product, promotion, promotionPrizeQuantity));
        int generalPrice = product.getTotalPrice(quantity) - promotionPrizePrice - promotionPrice;

        putPrices(prices, promotionPrizePrice, promotionPrice, generalPrice);
    }

    private int getPromotionPrizeQuantity(Product product, Promotion promotion, int quantity) {
        if (isProductInPromotion(product)) {
            if (product.isEnoughPromotionQuantity(quantity)) {
                return promotion.getPromotionPrize(quantity);
            }
            return promotion.getPromotionPrize(product.getPromotionQuantity());
        }
        return 0;
    }

    private int getPromotionQuantity(Product product, Promotion promotion, int promotionPrizeQuantity) {
        if (isProductInPromotion(product)) {
            return promotion.getPromotionQuantity(promotionPrizeQuantity);
        }
        return 0;
    }

    private void putPrices(Map<String, Integer> prices,
                           int promotionPrizePrice, int promotionPrice, int generalPrice) {
        prices.put(PriceType.PROMOTION_PRIZE_PRICE.getPrice(),
                prices.get(PriceType.PROMOTION_PRIZE_PRICE.getPrice()) + promotionPrizePrice);
        prices.put(PriceType.PROMOTION_PRICE.getPrice(),
                prices.get(PriceType.PROMOTION_PRICE.getPrice()) +promotionPrice);
        prices.put(PriceType.GENERAL_PRICE.getPrice(),
                prices.get(PriceType.GENERAL_PRICE.getPrice()) + generalPrice);
    }

    private boolean isProductInPromotion(Product product) {
        return productPromotions.containsKey(product);
    }
}
