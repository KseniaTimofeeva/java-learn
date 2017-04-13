package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 20.03.2017.
 */
public class LinkedList<T> implements Stack<T>, List<T> {
    private Item<T> head;
    private Item<T> last;
    private int size;
    private int modifications;

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head, modifications);
    }

    @Override
    public T poll() {
        size--;
        return remove(0);
    }

    @Override
    public void push(T value) {
        Item<T> next = head;
        head = new Item<>(value);
        head.next = next;
        size++;
    }

    @Override
    public boolean add(T value) {
        Item<T> item = new Item<>(value);
        if (head == null) {
            head = item;
        } else {
            last.next = item;
        }
        last = item;
        size++;
        modifications++;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new MyIndexOutOfBoundsExeption("Element with index " + index + " not found");
        }
        if (head != null) {
            int i = 0;
            Item<T> item = head;

            while (item != null) {
                if (i == index) {
                    return item.value;
                }
                item = item.next;
                i++;
            }
            return null;
        }
        return null;
    }

    @Override
    public T remove(int index) {
        if (head == null) {
            return null;
        }
        if (index == 0) {
            Item<T> h = head;
            head = head.next;
            size--;
            modifications++;
            return h.value;
        }
        int i = 0;
        Item<T> item = head;
        Item<T> prev = head;
        while (item != null) {
            if (i == index) {
                prev.next = item.next;
                size--;
                modifications++;
                return item.value;
            }
            prev = item;
            item = item.next;
            i++;
        }
        return null;
    }

    @Override
    public boolean set(int index, T o) {
        boolean isSet = false;
        if (head != null) {
            int i = 0;
            Item<T> item = head;

            while (item != null) {
                if (i == index) {
                    isSet = true;
                    modifications++;
                    item.value = o;
                }
                item = item.next;
                i++;
            }
        }
        return isSet;
    }

    @Override
    public int size() {
        return size;
    }

    private class LinkedListIterator<T> implements Iterator<T> {
        Item<T> next;
        int lastModifications;

        private LinkedListIterator(Item<T> head, int lastModifications) {
            next = head;
            this.lastModifications = lastModifications;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            if (lastModifications != modifications) {
                throw new MyConcurrentModificationExeption("List has been changed");
            }
            if (!hasNext()) {
                return null;
            }
            Item<T> n = next;
            next = next.next;
            return n.value;
        }

        @Override
        public void remove() {
        }
    }

    private static class Item<T> {
        T value;
        Item<T> next; //ссылка на следующий элемент списка

        Item(T value) {
            this.value = value;
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
        for (Object o : this) {
            result = 31 * result + o.hashCode();
            if (last.next != null) {
                result = 31 * result + last.next.hashCode();
            } else {
                result = 31 * result;
            }
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
