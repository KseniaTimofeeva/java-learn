package objects.linkedList;

import java.util.Iterator;

/**
 * Created by ksenia on 25.03.2017.
 */
public class MainArrayList {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(5);

        for (int i = 0; i < 2; i++) {
            arrayList.add(i);
        }

        for (Object o : arrayList) {
            System.out.print(o + " ");
        }
        System.out.println();

        arrayList.add(2, 2);
        arrayList.add(3, 3);
        for (Object o : arrayList) {
            System.out.print(o + " ");
        }
        System.out.println();
//        System.out.println(arrayList.add(5, 5));
        arrayList.add(1, 6);
        arrayList.remove(3);

        for (Object o : arrayList) {
            System.out.print(o + " ");
        }
        System.out.println();

        System.out.println(arrayList.get(0) + " " + arrayList.get(3) + " " + arrayList.get(4) + " " + arrayList.get(5));
    }
}
