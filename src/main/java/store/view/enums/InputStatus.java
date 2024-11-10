package store.view.enums;

public enum InputStatus {
    TRUE("Y", true),
    FALSE("N", false);

    private final String inputString;
    private final boolean inputValue;

    InputStatus(String inputString, boolean inputValue) {
        this.inputString = inputString;
        this.inputValue = inputValue;
    }

    public static boolean fromString(String input) {
        for (InputStatus status : InputStatus.values()) {
            if (status.inputString.equalsIgnoreCase(input)) {
                return status.inputValue;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.WRONG_INPUT.getMessage());
    }
}
