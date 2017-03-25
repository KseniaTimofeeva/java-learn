package objects.linkedList;

/**
 * Created by ksenia on 24.03.2017.
 */
public interface List extends Iterable {
    public boolean add(Object o);

    public Object get(int index);

    public Object remove(int index);

}
