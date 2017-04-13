package objects.linkedList;

/**
 * Created by ksenia on 12.04.2017.
 */
public class MyConcurrentModificationExeption extends RuntimeException {
    public MyConcurrentModificationExeption() {
        super();
    }

    public MyConcurrentModificationExeption(String message) {
        super(message);
    }

    public MyConcurrentModificationExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
