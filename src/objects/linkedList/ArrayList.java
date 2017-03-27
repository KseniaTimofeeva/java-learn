package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 25.03.2017.
 */
public class ArrayList implements List {
    private int capacity;
    private Object[] elementData;
    private int currentSize;

    public ArrayList() {
        capacity = 10;
        elementData = new Object[capacity];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            this.capacity = 10;
        } else {
            this.capacity = capacity;
            elementData = new Object[capacity];
        }
    }

    @Override
    public boolean add(Object object) {
        if (!checkCapacity()) {
            grow(capacity);
        }
        elementData[currentSize] = object;
        currentSize++;
        return true;
    }

    public boolean add(int index, Object object) {
        if (index > currentSize || index < 0) {
            return false;
        }
        if (!checkCapacity()) {
            grow(capacity);
        }
        if (index != currentSize) {
            System.arraycopy(elementData, index, elementData, index + 1, currentSize - index);
        }
        elementData[index] = object;
        currentSize++;
        return true;
    }

    private void grow(int capacity) {
        Object[] oldData = new Object[capacity];
        System.arraycopy(elementData, 0, oldData, 0, capacity);
        capacity *= 2;
        elementData = new Object[capacity];
        System.arraycopy(oldData, 0, elementData, 0, oldData.length);
    }

    @Override
    public Object get(int index) {
        if (index >= currentSize || index < 0) {
            return null;
        } else {
            return elementData[index];
        }
    }

    @Override
    public Object remove(int index) {
        if (index >= currentSize || index < 0) {
            return null;
        }
        int movedElements = currentSize - index - 1;
        if (movedElements > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, movedElements);
        }
        elementData[currentSize - 1] = null;
        currentSize--;
        return true;
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator(elementData, currentSize);
    }

    private boolean checkCapacity() {
        return currentSize < elementData.length;
    }
}
