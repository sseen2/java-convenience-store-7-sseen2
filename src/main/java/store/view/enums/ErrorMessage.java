package store.view.enums;

public enum ErrorMessage {
    NONEXISTENT_PRODUCT_INPUT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INVENTORY_QUANTITY_EXCEEDED_INPUT("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    PURCHASE_PRODUCT_FORMAT_ERROR_INPUT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    WRONG_INPUT("잘못된 입력입니다. 다시 입력해 주세요.");

    private static final String ERROR_HEADER = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = ERROR_HEADER + message;
    }

    public String getMessage() {
        return message;
    }
}
