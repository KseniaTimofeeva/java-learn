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

        Accumulator accumulator2 = new Accumulator(0, new Minus());
        System.out.println(accumulator2.calculate(10.5));
        System.out.println(accumulator2.calculate(15.5));
        System.out.println(accumulator2.getValue());
    }
}
