package objects.accumulator;

/**
 * Created by ksenia on 22.03.2017.
 */
public class TestAccumulator {
    public static void main(String[] args) {
        Accumulator accumulator1 = new Accumulator(0, new Plus());
        System.out.println(accumulator1.calculate(10));
        System.out.println(accumulator1.calculate(15));
        System.out.println(accumulator1.getValue());

        Accumulator accumulator2 = new Accumulator(1, new Mult());
        System.out.println(accumulator2.calculate(2.5));
        System.out.println(accumulator2.calculate(2.0));
        System.out.println(accumulator2.getValue());
    }
}
