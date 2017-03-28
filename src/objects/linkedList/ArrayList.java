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
        checkCapacity();
        elementData[currentSize] = object;
        currentSize++;
        return true;
    }

    public boolean add(int index, Object object) {
        if (index > currentSize || index < 0) {
            return false;
        }
        checkCapacity();
        if (index != currentSize) {
            System.arraycopy(elementData, index, elementData, index + 1, currentSize - index);
        }
        elementData[index] = object;
        currentSize++;
        return true;
    }

    @Override
    public Object get(int index) {
        if (index >= currentSize || index < 0) {
            return null;
        }
        return elementData[index];
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

    private void checkCapacity() {
        if (currentSize == elementData.length) {
            capacity *= 2;
            Object[] newElementData = new Object[capacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
    }

    public static class ArrayListIterator implements Iterator {
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
}
