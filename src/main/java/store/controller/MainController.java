package store.controller;

import store.domain.Orders;
import store.domain.Products;
import store.domain.Promotions;
import store.view.ResourceFileReadView;
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
        ResourceFileReadView fileReader = new ResourceFileReadView();
        promotions = new Promotions(fileReader.fileRead("promotions.md"));
        products = new Products(fileReader.fileRead("products.md"));
    }

    private void inputOrder() {
        OutputView.printPossessionGoods(products);
        orders = new Orders(InputView.inputGoodsNameQuantityOrder());
    }
}
