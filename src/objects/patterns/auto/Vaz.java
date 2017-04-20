package objects.patterns.auto;

/**
 * Created by ksenia on 19.04.2017.
 */
public class Vaz extends Factory {
    private static Vaz instance;

    public Vaz() {
    }

    @Override
    Car createCar() {
        return new Lada();
    }

    public static Factory makeVaz() {
        if (instance == null) {
            instance = new Vaz();
        }
        return instance;
    }
}
