package store.controller;

import store.Products;
import store.Promotions;
import store.ResourceFileRead;

import java.util.List;

public class MainController {
    private Promotions promotions;
    private Products products;

    public void run() {
        fileReadRun();
    }

    private void fileReadRun() {
        ResourceFileRead fileReader = new ResourceFileRead();
        promotions = new Promotions(fileReader.fileRead("promotions.md"));
        products = new Products(fileReader.fileRead("products.md"));
    }
}
