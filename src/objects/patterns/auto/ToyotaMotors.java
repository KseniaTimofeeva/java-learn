package objects.patterns.auto;

/**
 * Created by ksenia on 19.04.2017.
 */
public class ToyotaMotors extends Factory {
    private static ToyotaMotors instance;

    private ToyotaMotors() {
    }

    @Override
    Car createCar() {
        return new Toyota();
    }

    public static Factory makeToyota() {
        if (instance == null) {
            instance = new ToyotaMotors();
        }
        return instance;
    }
}
