package store.domain;

import store.view.enums.PriceType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class OrderProducts {
    private final Map<Order, Product> orderProducts;
    private int totalPrice;
    private int totalPromotionPrice;
    private int totalPromotionPrizePrice;
    private int totalGeneralPrice;
    private int totalMembershipPrice;

    public OrderProducts(Orders orders, Products products) {
        this.orderProducts = createOrderProducts(orders, products);
    }

    private Map<Order, Product> createOrderProducts(Orders orders, Products products) {
        Map<Order, Product> orderProducts = new LinkedHashMap<>();
        orderProducts.putAll(orders.putProducts(products.getProducts()));
        return orderProducts;
    }

    public List<Order> createReorders(ProductPromotions productPromotions) {
        List<Order> reorders = new ArrayList<>();
        orderProducts.forEach((order, product) -> {
            if (order.isReorder(product, productPromotions)) {
                reorders.add(order);
            }
        });
        return reorders;
    }

    public Map<Order, Integer> createOrderAgainQuantity(ProductPromotions productPromotions) {
        Map<Order, Integer> orderAgainQuantity = new LinkedHashMap<>();
        orderProducts.forEach((order, product) -> {
            int updateQuantity = order.orderAgainQuantity(product, productPromotions);
            if (updateQuantity > 0) {
                orderAgainQuantity.put(order, updateQuantity);
            }
        });
        return orderAgainQuantity;
    }

    public void setTotalPrice(ProductPromotions productPromotions) {
        Map<String, Integer> prices = setPrices();
        orderProducts.forEach((order, product) -> {
            order.setTotalPrice(prices, product, productPromotions);
        });

        setTotalPrice(prices);
    }

    private Map<String, Integer> setPrices() {
        Map<String, Integer> prices = new LinkedHashMap<>();
        prices.put(PriceType.PROMOTION_PRIZE_PRICE.getPrice(), 0);
        prices.put(PriceType.PROMOTION_PRICE.getPrice(), 0);
        prices.put(PriceType.GENERAL_PRICE.getPrice(), 0);
        return prices;
    }

    private void setTotalPrice(Map<String, Integer> prices) {
        totalPromotionPrizePrice = prices.get(PriceType.PROMOTION_PRIZE_PRICE.getPrice());
        totalPromotionPrice = prices.get(PriceType.PROMOTION_PRICE.getPrice());
        totalGeneralPrice = prices.get(PriceType.GENERAL_PRICE.getPrice());
        totalPrice = totalPromotionPrizePrice + totalPromotionPrice + totalGeneralPrice;
    }

    public void setMembershipPrice() {
        totalMembershipPrice = (totalGeneralPrice / 10) * 3;
        if (totalMembershipPrice > 8000) {
            totalMembershipPrice = 8000;
        }
        totalGeneralPrice -= totalMembershipPrice;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Map.Entry<Order, Product> entry : orderProducts.entrySet()) {
            totalQuantity += entry.getKey().getQuantity();
        }
        return totalQuantity;
    }

    public boolean hasPromotionPrize() {
        return totalPromotionPrizePrice != 0;
    }

    public String getTotalPrice() {
        return String.format("%,d", totalPrice);
    }

    public String getTotalPromotionPrizePrice() {
        return String.format("%,d", -totalPromotionPrizePrice);
    }

    public String getTotalMembershipPrice() {
        return String.format("%,d", -totalMembershipPrice);
    }

    public String getTotalPayPrice() {
        return String.format("%,d", totalPromotionPrice + totalGeneralPrice);
    }
}
