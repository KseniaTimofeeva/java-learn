package objects.linkedList;

/**
 * Created by ksenia on 27.03.2017.
 */
public interface Transformer<T, R> {
    R apply(T o);
}
