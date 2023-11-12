package christmas.exception;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static <T> T createValidObject(Supplier<T> supplier, Consumer<Exception> exceptionConsumer) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException | IllegalStateException exception) {
                exceptionConsumer.accept(exception);
            }
        }
    }
}
