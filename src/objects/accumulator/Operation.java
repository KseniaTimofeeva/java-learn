package objects.accumulator;

/**
 * Created by ksenia on 22.03.2017.
 */
public interface Operation {
    public int apply(int a1, int a2);

    public double apply(double a1, double a2);

    public long apply(long a1, long a2);
}
