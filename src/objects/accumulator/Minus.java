package objects.accumulator;

/**
 * Created by ksenia on 22.03.2017.
 */
public class Minus extends Operation {

    @Override
    public int apply(int a, int b) {
        return a - b;
    }

    @Override
    public double apply(double a, double b) {
        return a - b;
    }

    @Override
    public long apply(long a, long b) {
        return a - b;
    }
}
