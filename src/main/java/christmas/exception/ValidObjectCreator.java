package christmas.exception;

import java.util.function.Supplier;

import christmas.view.OutputView;

public class ValidObjectCreator {

    private ValidObjectCreator() {
    }

    public static <T> T createValidObject(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException | IllegalStateException exception) {
                OutputView.printExceptionMessage(exception);
            }
        }
    }
}
