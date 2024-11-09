package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<Product> products;

    public Products(List<ConvenienceStore> products) {
        this.products = castToProduct(products);
    }

    private List<Product> castToProduct(List<ConvenienceStore> products) {
        List<Product> castToProduct = new ArrayList<>();
        for (ConvenienceStore product : products) {
            castToProduct.add((Product) product);
        }

        return castToProduct;
    }

    public List<Product> returnPromotionProducts() {
        List<Product> promotionProducts = new ArrayList<>();
        for (Product product : products) {
            if (!product.isPromotionNull()) {
                promotionProducts.add(product);
            }
        }
        return promotionProducts;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void printProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }
}
