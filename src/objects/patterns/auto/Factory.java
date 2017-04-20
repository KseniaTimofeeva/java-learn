package objects.patterns.auto;

/**
 * Created by ksenia on 19.04.2017.
 */
public abstract class Factory {

    abstract Car createCar();


    static Factory getFactory(String region) {

        if (region.equals("Japan")) {
            return ToyotaMotors.makeToyota();
        } else if (region.equals("Germany")) {
            return BavariaMotors.makeBavariaMotors();
        } else if (region.equals("Russia")) {
            return Vaz.makeVaz();
        } else {
            throw new RuntimeException("");
        }
    }


}
