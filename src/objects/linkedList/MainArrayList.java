package objects.linkedList;

import java.util.Random;

/**
 * Created by ksenia on 25.03.2017.
 */
public class MainArrayList {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(5);

        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            arrayList.add(rnd.nextInt(20));
        }
        UtilityClass.listOutput(arrayList);

        arrayList.add(2, 6);
        arrayList.add(3, 7);
        UtilityClass.listOutput(arrayList);

        arrayList.add(1, 6);
        arrayList.remove(5);
        UtilityClass.listOutput(arrayList);

        System.out.println(arrayList.get(0) + " " + arrayList.get(3) + " " + arrayList.get(4) + " " + arrayList.get(5));


        //UtilityClass
//        find
        System.out.println("find: " + UtilityClass.find(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return (int) o == 3;
            }
        }, arrayList));
        System.out.println();

//        filter
        List filteredList = UtilityClass.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return o.toString().length() > 1;
            }
        }, arrayList);
        UtilityClass.listOutput(filteredList);

//        transform
        List transformedList = UtilityClass.transform(new Transformer() {
            @Override
            public Object apply(Object o) {
                return o.toString() + o.toString();
            }
        }, arrayList);
        UtilityClass.listOutput(transformedList);
    }
}
