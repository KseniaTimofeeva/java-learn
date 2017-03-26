package objects.accumulator;

/**
 * Created by ksenia on 26.03.2017.
 */
public class Divide implements Operation {

    @Override
    public int apply(int a1, int a2) {
        if (a2 == 0){
            System.out.println("Divide by zero");
            System.exit(-1);
        }
        return a1 / a2;
    }

    @Override
    public double apply(double a1, double a2) {
        if (a2 == 0.0){
            System.out.println("Divide by zero");
            System.exit(-1);
        }
        return a1 / a2;
    }

    @Override
    public long apply(long a1, long a2) {
        if (a2 == 0){
            System.out.println("Divide by zero");
            System.exit(-1);
        }
        return a1 / a2;
    }
}
