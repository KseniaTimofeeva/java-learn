package objects.patterns.auto;

/**
 * Created by ksenia on 19.04.2017.
 */
public class Lada implements Car {

    @Override
    public boolean drive(int distance) {
        System.out.print("Lada:");
        return true;
    }
}
