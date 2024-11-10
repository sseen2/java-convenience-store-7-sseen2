package store.controller;

import store.domain.Orders;
import store.domain.ProductPromotions;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.OrderProducts;
import store.view.ResourceFileReadView;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private Promotions promotions;
    private Products products;
    private Orders orders;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ProductPromotions productPromotions = setPromotionProducts();
        OrderProducts orderProducts = setOrderProducts();
    }

    private ProductPromotions setPromotionProducts() {
        fileReadRun();
        return new ProductPromotions(promotions, products);
    }

    private void fileReadRun() {
        ResourceFileReadView fileReader = new ResourceFileReadView();
        promotions = new Promotions(fileReader.fileRead("promotions.md"));
        products = new Products(fileReader.fileRead("products.md"));
    }

    private OrderProducts setOrderProducts() {
        outputView.printPossessionGoods(products);
        while (true) {
            try {
                inputOrder();
                return new OrderProducts(orders, products);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void inputOrder() {
        orders = new Orders(inputView.inputGoodsNameQuantityOrder());
    }
}
