package objects.accumulator;

/**
 * Created by ksenia on 28.03.2017.
 */
public class NRoot implements Operation {
    @Override
    public int apply(int a1, int a2) {
        System.out.println("Требуется double");
        System.exit(-1);
        return -1;
    }

    @Override
    public double apply(double a1, double a2) {
        if (a2 == 0.0){
            System.out.println("Divide by zero");
            System.exit(-1);
        }
        System.out.println("double");
        return Math.pow(a1, 1.0 / a2);
    }

    @Override
    public long apply(long a1, long a2) {
        System.out.println("Требуется double");
        System.exit(-1);
        return -1;
    }
}
