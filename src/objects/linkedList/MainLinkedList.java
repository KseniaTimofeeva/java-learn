package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 24.03.2017.
 */
public class MainLinkedList {
    public static void main(String[] args) {
//        LinkedList
        List list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            System.out.print(o + " ");
        }
        System.out.println();

        System.out.print(list.get(0) + " " + list.get(9) + " " + list.get(5));
//        System.out.println(list.get(-3));
//        System.out.println(list.get(15));
        System.out.println();

        list.remove(9);
        list.remove(5);
        list.remove(0);

        iterator = list.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            System.out.print(o + " ");
        }
        System.out.println();

//        Stack
        Stack stack = new LinkedList();
        for (int i = 10; i < 20; i++) {
            stack.push(i);
        }

        System.out.print(stack.poll() + " " + stack.poll() + " " + stack.poll());
    }
}
