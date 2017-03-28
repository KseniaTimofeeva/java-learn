package objects.linkedList;

import java.util.Random;

/**
 * Created by ksenia on 24.03.2017.
 */
public class MainLinkedList {
    public static void main(String[] args) {
//        LinkedList
        List list = new LinkedList();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(rnd.nextInt(20));
        }

        UtilityClass.listOutput(list);

        System.out.print(list.get(0) + " " + list.get(9) + " " + list.get(5));
//        System.out.println(list.get(-3));
//        System.out.println(list.get(15));
        System.out.println();

        System.out.print(list.remove(9) + " " + list.remove(5) + " " + list.remove(0));
        System.out.println();

        UtilityClass.listOutput(list);

//        Stack
        Stack stack = new LinkedList();
        for (int i = 10; i < 20; i++) {
            stack.push(i);
        }

        System.out.print(stack.poll() + " " + stack.poll() + " " + stack.poll());
        System.out.println();
        System.out.println();


//        UtilityClass
//        find
        System.out.println("find: " + UtilityClass.find(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return (int) o == 3;
            }
        }, list));
        System.out.println();

//        filter
        List filteredList = UtilityClass.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return o.toString().length() > 1;
            }
        }, list);
        UtilityClass.listOutput(filteredList);

//        transform
        List transformedList = UtilityClass.transform(new Transformer() {
            @Override
            public Object apply(Object o) {
                return o.toString() + o.toString();
            }
        }, list);
        UtilityClass.listOutput(transformedList);
    }
}
