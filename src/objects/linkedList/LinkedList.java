package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 20.03.2017.
 */
public class LinkedList implements Stack, List {
    private Item head;
    private Item last;
    private int size;

    @Override
    public Iterator iterator() {
        return new LinkedListIterator(head);
    }

    @Override
    public Object poll() {
        size--;
        return remove(0);
    }

    @Override
    public void push(Object value) {
        Item next = head;
        head = new Item(value);
        head.next = next;
        size++;
    }

    @Override
    public boolean add(Object value) {
        Item item = new Item(value);
        if (head == null) {
            head = item;
        } else {
            last.next = item;
        }
        last = item;
        size++;
        return true;
    }

    @Override
    public Object get(int index) {
        if (head != null) {
            int i = 0;
            Item item = head;

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
    public Object remove(int index) {
        if (head != null) {
            if (index == 0) {
                Item h = head;
                head = head.next;
                size--;
                return h.value;
            }
            int i = 0;
            Item item = head;
            Item prev = head;
            while (item != null) {
                if (i == index) {
                    prev.next = item.next;
                    size--;
                    return item.value;
                }
                prev = item;
                item = item.next;
                i++;
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean set(int index, Object o) {
        boolean isSet = false;
        if (head != null) {
            int i = 0;
            Item item = head;

            while (item != null) {
                if (i == index) {
                    isSet = true;
                    item.value = o;
                }
                item = item.next;
                i++;
            }
        }
        return isSet;
    }

    private static class LinkedListIterator implements Iterator {
        Item next;

        private LinkedListIterator(Item head) {
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

    private static class Item {
        Object value;
        Item next; //ссылка на следующий элемент списка

        Item(Object value) {
            this.value = value;
        }
    }

    public int getSize() {
        return size;
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
        if (getSize() != list.getSize()) {
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
