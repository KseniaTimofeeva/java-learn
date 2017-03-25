package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 24.03.2017.
 */
public class LinkedListIterator implements Iterator {
    Item previous;
    Item next;

    public LinkedListIterator(Item head) {
        next = head;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            return null;
        }
        Item n = next;
        next = next.next;
        return n.value;
    }

    @Override
    public void remove() {

    }
}
