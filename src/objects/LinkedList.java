package objects;

/**
 * Created by ksenia on 20.03.2017.
 */
public class LinkedList {
    Item head;
    Item last;
    private int size;

    public int size() {
        return size;
    }

    public void add(String addVal) {
        Item item = new Item();
        item.value = addVal;
        if (head == null) {
            head = item;
            last = item;
        } else {
            last.next = item;
            last = item;
        }
        size++;
    }

    public String get(int index) {
        if (head != null) {
            int i = 0;
            Item item = head;

            while ( item!= null) {
                if (i == index) {
                    return item.value;
                }
                item = item.next;
                i++;
            }
            throw new IndexOutOfBoundsException(index + " from " + (i - 1));
        } else {
            throw new IndexOutOfBoundsException(index + " from 0");
        }
    }

    public void remove(int index) {
        if (head != null) {
            if (index == 0) {
                head = head.next;
                size--;
                return;
            }
            int i = 0;
            Item item = head;
            Item prev = head;
            while (item != null) {
                if (i == index) {
                    prev.next = item.next;
                    size--;
                    return;
                }
                prev = item;
                item = item.next;
                i++;
            }
            throw new IndexOutOfBoundsException(index + " from " + (i - 1));
        } else {
            throw new IndexOutOfBoundsException(index + " from 0");
        }
    }
}
