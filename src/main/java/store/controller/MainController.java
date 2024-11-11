package store.controller;

import store.domain.*;
import store.view.ResourceFileReadView;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;
import java.util.Map;

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

        askWhether(orderProducts, productPromotions);
    }

    private void askWhether(OrderProducts orderProducts, ProductPromotions productPromotions) {
        promotionBenefit(orderProducts, productPromotions);
        promotionNotApplicable(orderProducts, productPromotions);
        setTotalPrice(orderProducts, productPromotions);
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

    private void promotionBenefit(OrderProducts orderProducts, ProductPromotions productPromotions) {
        while (true) {
            try {
                addOrderQuantity(orderProducts, productPromotions);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addOrderQuantity(OrderProducts orderProducts, ProductPromotions productPromotions) {
        List<Order> reorders = orderProducts.createReorders(productPromotions);
        for (Order order : reorders) {
            if (inputPromotionBenefit(order.getName())) {
                order.setQuantity(1);
            }
        }
    }

    private boolean inputPromotionBenefit(String name) {
        return inputView.inputPromotionBenefitGuide(name);
    }

    private void promotionNotApplicable(OrderProducts orderProducts, ProductPromotions productPromotions) {
        while (true) {
            try {
                updateOrderQuantity(orderProducts, productPromotions);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void updateOrderQuantity(OrderProducts orderProducts, ProductPromotions productPromotions) {
        Map<Order, Integer> reorders = orderProducts.createOrderAgainQuantity(productPromotions);
        reorders.forEach((order, updateQuantity) -> {
            if (!inputPromotionNotApplicable(order.getName(), updateQuantity)) {
                order.setQuantity(-updateQuantity);
            }
        });
    }

    private boolean inputPromotionNotApplicable(String name, int quantity) {
        return inputView.inputPromotionNotApplicable(name, quantity);
    }

    private void setTotalPrice(OrderProducts orderProducts, ProductPromotions productPromotions) {
        orderProducts.setTotalPrice(productPromotions);
    }
}
