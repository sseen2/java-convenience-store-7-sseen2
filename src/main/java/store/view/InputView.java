package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.Order;
import store.view.enums.ErrorMessage;
import store.view.enums.InputStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String ORDERS_SPLIT_STRING = ",";
    private static final String ORDER_SPLIT_STRING = "[\\[\\]-]";

    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public List<Order> inputGoodsNameQuantityOrder() {
        outputView.printGoodsNameQuantityOrder();
        return splitOrders(Arrays.stream(Console.readLine().split(ORDERS_SPLIT_STRING)).toList());
    }

    private List<Order> splitOrders(List<String> orderString) {
        List<Order> orders = new ArrayList<>();
        for (String order : orderString) {
            orders.add(createOrder(Arrays.stream(order.split(ORDER_SPLIT_STRING)).toList()));
        }
        return orders;
    }

    private Order createOrder(List<String> order) {
        validateOrderInput(order);

        String name = order.get(1);
        int quantity = Integer.parseInt(order.get(2));
        return new Order(name, quantity);
    }

    private void validateOrderInput(List<String> order) {
        if (order.size() != 3 || !isNumeric(order.get(2))) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_PRODUCT_FORMAT_ERROR_INPUT.getMessage());
        }
    }

    private boolean isNumeric(String validateString) {
        try {
            Integer.parseInt(validateString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean inputPromotionBenefitGuide(String productName) {
        outputView.printPromotionBenefitGuide(productName);
        return validateInput(Console.readLine());
    }

    public boolean inputPromotionNotApplicable(String name, int quantity) {
        outputView.printPromotionNotApplicableGuide(name, quantity);
        return validateInput(Console.readLine());
    }

    public boolean inputMembershipDiscount() {
        outputView.printMembershipDiscount();
        return validateInput(Console.readLine());
    }

    private boolean validateInput(String input) {
        if (!input.equals("Y") && !input.equals("N")) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_INPUT.getMessage());
        }
        return InputStatus.fromString(input);
    }
}
