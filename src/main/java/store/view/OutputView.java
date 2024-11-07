package store.view;

import store.Products;

public class OutputView {
    private static final String CONVENIENCE_STORE_NAME = "W편의점";
    public static void printPossessionGoods(Products products) {
        System.out.println(
                String.format("안녕하세요. %s입니다.\n현재 보유하고 있는 상품입니다.\n", CONVENIENCE_STORE_NAME)
        );
        products.printProducts();
    }
}
