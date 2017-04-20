package objects.patterns.auto;

/**
 * Created by ksenia on 20.04.2017.
 */
public class FactoryTest {
    public static void main(String[] args) {
        Car germany = Factory.getFactory("Germany").createCar();
        Car japan = Factory.getFactory("Japan").createCar();
        Car russia = Factory.getFactory("Russia").createCar();

        System.out.println(germany.drive(10));
        System.out.println(japan.drive(11));
        System.out.println(russia.drive(15));
    }
}
