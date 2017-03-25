package objects.adder;

/**
 * Created by ksenia on 20.03.2017.
 */
public class TestObjects {
    public static void main(String[] args) {
//       Adder
        System.out.println("Adder:");
        Adder adder = new Adder(10);
        adder.increment();
        System.out.println(adder.getValue());
        adder.add(10);
        System.out.println(adder.getValue());
        System.out.println();
    }
}
