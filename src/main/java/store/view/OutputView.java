package store.view;

import store.domain.Products;

public class OutputView {
    private static final String CONVENIENCE_STORE_NAME = "W편의점";

    public void printPossessionGoods(Products products) {
        System.out.println(
                String.format("안녕하세요. %s입니다.\n현재 보유하고 있는 상품입니다.\n", CONVENIENCE_STORE_NAME)
        );
        products.printProducts();
    }

    public void printGoodsNameQuantityOrder() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public void printPromotionBenefitGuide(String productName) {
        System.out.println(
                String.format("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)", productName)
        );
    }

    public void printPromotionNotApplicableGuide(String productName, int quantity) {
        System.out.println(
                String.format("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)",
                        productName,
                        quantity)
        );
    }

    public void printMembershipDiscount() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }
}
