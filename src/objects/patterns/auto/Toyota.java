package objects.patterns.auto;

/**
 * Created by ksenia on 19.04.2017.
 */
public class Toyota implements Car {

    @Override
    public boolean drive(int distance) {
        System.out.print("Toyota:");
        return true;
    }
}
