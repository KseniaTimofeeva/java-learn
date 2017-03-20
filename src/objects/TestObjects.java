package objects;

/**
 * Created by ksenia on 20.03.2017.
 */
public class TestObjects {
    public static void main(String[] args) {
//       Adder
        Adder adder = new Adder(10);
        adder.increment();
        System.out.println(adder.getValue());
        adder.add(10);
        System.out.println(adder.getValue());
        System.out.println();

//        LinkedList
        LinkedList list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        System.out.println(list.get(0));
        System.out.println(list.get(9));
        System.out.println(list.get(5));
//        System.out.println(list.get(-3));
//        System.out.println(list.get(15));
        System.out.println();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();

        list.remove(9);
        list.remove(5);
        list.remove(0);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
