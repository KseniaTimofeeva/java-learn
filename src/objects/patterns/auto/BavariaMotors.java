package objects.patterns.auto;

/**
 * Created by ksenia on 19.04.2017.
 */
public class BavariaMotors extends Factory {

    private static BavariaMotors instance;

    private BavariaMotors() {

    }

    @Override
    Car createCar() {
        return new BMW();
    }

    public static Factory makeBavariaMotors() {
            if (instance == null) {
                instance = new BavariaMotors();
            }
            return instance;
    }
}
