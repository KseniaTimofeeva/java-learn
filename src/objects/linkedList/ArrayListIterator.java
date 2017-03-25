package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 25.03.2017.
 */
public class ArrayListIterator implements Iterator {
    private Object[] elementData;
    private int currentSize;
    private int nextIndex;

    public ArrayListIterator(Object[] elementData, int currentSize) {
        this.elementData = elementData;
        this.currentSize = currentSize;
    }

    @Override
    public boolean hasNext() {
        return nextIndex < currentSize;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            return null;
        }
        int n = nextIndex;
        nextIndex++;
        return elementData[n];
    }

    @Override
    public void remove() {
    }
}
