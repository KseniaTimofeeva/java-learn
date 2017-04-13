package objects.exceptions;

/**
 * Created by ksenia on 14.04.2017.
 */
public class DivideByZeroExсeption extends Exception {
    public DivideByZeroExсeption() {
        super();
    }

    public DivideByZeroExсeption(String message) {
        super(message);
    }

    public DivideByZeroExсeption(String message, Throwable cause) {
        super(message, cause);
    }
}
