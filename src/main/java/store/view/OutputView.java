package store.view;

import store.domain.OrderProducts;
import store.domain.Products;

public class OutputView {
    private static final String CONVENIENCE_STORE_NAME = "W 편의점";
    private static final String TAB = "\t\t";

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

    public void printReceiptOrder(OrderProducts orderProducts) {
        System.out.println("==========" + CONVENIENCE_STORE_NAME + "=========");

        printOrderList(orderProducts);
        if (orderProducts.hasPromotionPrize()) {
            printPromotionPrize(orderProducts);
        }
        printReceiptOrderPrice(orderProducts);
    }

    private void printOrderList(OrderProducts orderProducts) {
        System.out.println("상품명" + TAB + "수량" + TAB + "금액");
        orderProducts.printOrderList();
    }

    private void printPromotionPrize(OrderProducts orderProducts) {
        System.out.println("========== 증 정 ==========");
        orderProducts.printPromotionPrize();
    }

    private void printReceiptOrderPrice(OrderProducts orderProducts) {
        System.out.println("==========================");
        System.out.println("총구매액" + TAB + orderProducts.getTotalQuantity() + TAB + orderProducts.getTotalPrice());
        System.out.println("행사할인" + TAB + TAB + orderProducts.getTotalPromotionPrizePrice());
        System.out.println("멤버십할인\t" + TAB + orderProducts.getTotalMembershipPrice());
        System.out.println("내실돈" + TAB + TAB + orderProducts.getTotalPayPrice());
    }

    public void printAdditionalPurchaseGuide() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
    }
}
