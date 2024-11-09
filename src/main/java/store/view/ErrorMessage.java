package store.view;

public enum ErrorMessage {
    NONEXISTENT_PRODUCT_INPUT("존재하지 않는 상품입니다. 다시 입력해 주세요.");

    private static final String ERROR_HEADER = "[Error] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = ERROR_HEADER + message;
    }

    public String getMessage() {
        return message;
    }
}
