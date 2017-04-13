package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 25.03.2017.
 */
public class ArrayList<T> implements List<T> {
    private int capacity;
    private Object[] elementData;
    private int currentSize;
    private int modifications;

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
    public boolean add(T object) {
        checkCapacity();
        elementData[currentSize] = object;
        currentSize++;
        modifications++;
        return true;
    }

    public boolean add(int index, T object) {
        if (index > currentSize || index < 0) {
            return false;
        }
        checkCapacity();
        if (index != currentSize) {
            System.arraycopy(elementData, index, elementData, index + 1, currentSize - index);
        }
        elementData[index] = object;
        modifications++;
        currentSize++;
        return true;
    }

    @Override
    public boolean set(int index, T o) {
        if (index >= currentSize || index < 0) {
            return false;
        }
        elementData[index] = o;
        modifications++;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= currentSize || index < 0) {
            throw new MyIndexOutOfBoundsExeption("Element with index " + index + " not found");
        }
        return (T)elementData[index];
    }

    @Override
    public T remove(int index) {
        if (index >= currentSize || index < 0) {
            return null;
        }
        T result = (T) elementData[index];
        int movedElements = currentSize - index - 1;
        if (movedElements > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, movedElements);
        }
        elementData[currentSize - 1] = null;
        modifications++;
        currentSize--;
        return result;
    }

    public int size() {
        return currentSize;
    }

    private void checkCapacity() {
        if (currentSize == elementData.length) {
            capacity *= 2;
            Object[] newElementData = new Object[capacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(elementData, currentSize, modifications);
    }

    public class ArrayListIterator<T> implements Iterator<T> {
        private Object[] elementData;
        private int currentSize;
        private int nextIndex;
        private int lastModifications;

        private ArrayListIterator(Object[] elementData, int currentSize, int lastModifications) {
            this.elementData = elementData;
            this.currentSize = currentSize;
            this.lastModifications = lastModifications;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < currentSize;
        }

        @Override
        public T next() {
            if (lastModifications != modifications) {
                throw new MyConcurrentModificationExeption("List has been changed");
            }
            if (!hasNext()) {
                return null;
            }
            int n = nextIndex;
            nextIndex++;
            return (T)elementData[n];
        }

        @Override
        public void remove() {
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass().getSuperclass() != obj.getClass().getSuperclass()) {
            return false;
        }
        List list = (List) obj;
        if (size() != list.size()) {
            return false;
        }
        Iterator iter1 = iterator();
        Iterator iter2 = list.iterator();
        while (iter1.hasNext()) {
            Object o1 = iter1.next();
            Object o2 = iter2.next();
            if (!o1.equals(o2)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        int nextIndex = 0;
        for (Object o : this) {
            result = 31 * result + o.hashCode();
            if (elementData[nextIndex] != null) {
                result = 31 * result + elementData[nextIndex].hashCode();
            } else {
                result = 31 * result;
            }
            nextIndex++;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("[");
        int i = 0;
        for (Object o : this) {
            if (i == 0) {
                strBuild.append(o);
            } else {
                strBuild.append(", ").append(o);
            }
            i++;
        }
        strBuild.append("]");
        return strBuild.toString();
    }
}
