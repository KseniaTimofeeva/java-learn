package objects.exceptions;

/**
 * Created by ksenia on 13.04.2017.
 */
public class WrongExpressionException extends Exception{
    public WrongExpressionException() {
        super();
    }

    public WrongExpressionException(String message) {
        super(message);
    }

    public WrongExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
