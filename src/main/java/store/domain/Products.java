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

        return updateProducts(castToProduct);
    }

    private List<Product> updateProducts(List<Product> products) {
        List<Product> updateProducts = new ArrayList<>();
        for (Product product : products) {
            updateProducts.add(product);
            addGeneralStock(products, updateProducts, product);
        }

        return updateProducts;
    }

    private void addGeneralStock(List<Product> products,
                                 List<Product> updateProducts,
                                 Product product) {
        if (!product.isPromotionNull() && !hasGeneralStock(products, product)) {
            updateProducts.add(new Product(product));
        }
    }

    private boolean hasGeneralStock(List<Product> products, Product product) {
        for (Product otherProduct : products) {
            if (otherProduct instanceof Product other &&
                    other.isNameEquals(product) && other.isPromotionNull()) {
                return true;
            }
        }

        return false;
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

    public List<Product> returnNotPromotionProducts() {
        List<Product> notPromotionProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isPromotionNull()) {
                notPromotionProducts.add(product);
            }
        }
        return notPromotionProducts;
    }

    public void printProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }
}
