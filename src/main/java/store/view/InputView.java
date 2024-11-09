package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String ORDERS_SPLIT_STRING = ",";
    private static final String ORDER_SPLIT_STRING = "[\\[\\]-]";

    public static List<Order> inputGoodsNameQuantityOrder() {
        OutputView.printGoodsNameQuantityOrder();
        return splitOrders(Arrays.stream(Console.readLine().split(ORDERS_SPLIT_STRING)).toList());
    }

    private static List<Order> splitOrders(List<String> orderString) {
        List<Order> orders = new ArrayList<>();
        for (String order : orderString) {
            orders.add(createOrder(Arrays.stream(order.split(ORDER_SPLIT_STRING)).toList()));
        }

        return orders;
    }

    private static Order createOrder(List<String> order) {
        String name = order.get(1);
        int quantity = Integer.parseInt(order.get(2));
        return new Order(name, quantity);
    }

    public static void inputPromotionBenefitGuide(String productName) {
        OutputView.printPromotionBenefitGuide(productName);
    }
}
