package objects.linkedList;

/**
 * Created by ksenia on 24.03.2017.
 */
public interface List<T> extends Iterable<T> {
    boolean add(T o);

    T get(int index);

    T remove(int index);

    int size();

    boolean set(int index, T o);

}
