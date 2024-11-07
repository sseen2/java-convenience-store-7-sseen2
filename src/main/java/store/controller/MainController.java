package store.controller;

import store.model.Orders;
import store.model.Products;
import store.model.Promotions;
import store.ResourceFileRead;
import store.view.InputView;
import store.view.OutputView;

public class MainController {
    private Promotions promotions;
    private Products products;
    private Orders orders;

    public void run() {
        fileReadRun();
        inputOrder();
    }

    private void fileReadRun() {
        ResourceFileRead fileReader = new ResourceFileRead();
        promotions = new Promotions(fileReader.fileRead("promotions.md"));
        products = new Products(fileReader.fileRead("products.md"));
    }

    private void inputOrder() {
        OutputView.printPossessionGoods(products);
        orders = new Orders(InputView.inputGoodsNameQuantityOrder());
    }
}
