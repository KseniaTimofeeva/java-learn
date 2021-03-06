package objects.accumulator;

import java.util.Arrays;

/**
 * Created by ksenia on 22.03.2017.
 */
public class TestAccumulator {
    public static void main(String[] args) {
        Accumulator accumulator1 = new Accumulator(0, new Plus());
        System.out.println(accumulator1.calculate(10));
        System.out.println(accumulator1.calculate(15));
        System.out.println(accumulator1.getValue());
        System.out.println();

        Accumulator accumulator2 = new Accumulator(1, new Mult());
        System.out.println(accumulator2.calculate(2.5));
        System.out.println(accumulator2.calculate(2.0));
        System.out.println(accumulator2.getValue());
        System.out.println();

        Accumulator accumulator3 = new Accumulator(10, new Divide());
        System.out.println(accumulator3.calculate(5));
        System.out.println(accumulator3.calculate(2.0));
        System.out.println(accumulator3.getValue());
        System.out.println();

        Accumulator accumulator4 = new Accumulator(new int[]{0, 0, 3, 3}, new Operation[]{new Plus(), new Plus(), new Pow(), new Mult()});
        System.out.println(Arrays.toString(accumulator4.calculateArray(2)));
        System.out.println(Arrays.toString(accumulator4.calculateArray(2)));
        System.out.println();

        Accumulator accumulator5 = new Accumulator(-8, new Operation() {
            @Override
            public int apply(int a1, int a2) {
                return a1 - a2;
            }

            @Override
            public double apply(double a1, double a2) {
                return a1 - a2;
            }

            @Override
            public long apply(long a1, long a2) {
                return a1 - a2;
            }
        });
        System.out.println(accumulator5.calculate(3.0));
    }
}
