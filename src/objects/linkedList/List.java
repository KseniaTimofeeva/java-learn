package objects.linkedList;

/**
 * Created by ksenia on 24.03.2017.
 */
public interface List extends Iterable {
    boolean add(Object o);

    Object get(int index);

    Object remove(int index);

}
