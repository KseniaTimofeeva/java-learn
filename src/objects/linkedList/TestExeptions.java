package objects.linkedList;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by ksenia on 12.04.2017.
 */
public class TestExeptions {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list2.add(i);
        }


        assert list1.get(2) == 1: "Element is wrong";
//        list2.get(15);

        Iterator<Integer> iter1 = list1.iterator();
        while (iter1.hasNext()) {
            iter1.next();
//            list1.remove(9);
//            list1.set(5, 5);
//            list1.add(10);
        }

        Iterator<Integer> iter2 = list2.iterator();
        while (iter2.hasNext()) {
            iter2.next();
            list2.add(10);
            list2.remove(6);
        }
    }
}
