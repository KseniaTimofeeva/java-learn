package objects.linkedList;

/**
 * Created by ksenia on 12.04.2017.
 */
public class MyIndexOutOfBoundsExeption extends RuntimeException {
    public MyIndexOutOfBoundsExeption() {
        super();
    }

    public MyIndexOutOfBoundsExeption(String message) {
        super(message);
    }

    public MyIndexOutOfBoundsExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
