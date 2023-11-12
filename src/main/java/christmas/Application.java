package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        new ChristmasController(new InputView(), new OutputView()).play();
    }
}
