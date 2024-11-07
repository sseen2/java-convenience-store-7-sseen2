package store;

import java.util.List;

public class Products {
    private final List<ConvenienceStore> products;

    public Products(List<ConvenienceStore> products) {
        this.products = products;
    }

    public void printProducts() {
        for (ConvenienceStore product : products) {
            System.out.println(product.toString());
        }
        System.out.println();
    }
}
