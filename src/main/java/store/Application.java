package store;

import store.controller.MainController;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(outputView);
        MainController main = new MainController(inputView, outputView);
        main.run();
    }
}
