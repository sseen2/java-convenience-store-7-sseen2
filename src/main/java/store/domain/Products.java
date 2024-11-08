package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<ConvenienceStore> products;

    public Products(List<ConvenienceStore> products) {
        this.products = updateProducts(products);
    }

    private List<ConvenienceStore> updateProducts(List<ConvenienceStore> products) {
        List<ConvenienceStore> updateProducts = new ArrayList<>();
        for (ConvenienceStore convenienceStore : products) {
            updateProducts.add(convenienceStore);
            checkPromotion(products, updateProducts, convenienceStore);
        }

        return updateProducts;
    }

    private void checkPromotion(List<ConvenienceStore> products,
                                                   List<ConvenienceStore> updateProducts,
                                                   ConvenienceStore convenienceStore) {
        if (convenienceStore instanceof Product product && !product.isPromotionNull()) {
            addGeneralStock(products, updateProducts, product);
        }
    }

    private void addGeneralStock(List<ConvenienceStore> products,
                                        List<ConvenienceStore> updateProducts,
                                        Product product) {
        if (!hasGeneralStock(products, product)) {
            updateProducts.add(new Product(product));
        }
    }

    private boolean hasGeneralStock(List<ConvenienceStore> products, Product product) {
        for (ConvenienceStore otherProduct : products) {
            if (otherProduct instanceof Product other &&
                other.isNameEquals(product) && other.isPromotionNull()) {
                return true;
            }
        }

        return false;
    }

    public void printProducts() {
        for (ConvenienceStore product : products) {
            System.out.println(product.toString());
        }
        System.out.println();
    }
}
