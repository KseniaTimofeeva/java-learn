package objects.exceptions;

/**
 * Created by ksenia on 14.04.2017.
 */
public class NoAvailableValueException extends Exception {
    public NoAvailableValueException() {
        super();
    }

    public NoAvailableValueException(String message) {
        super(message);
    }

    public NoAvailableValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
