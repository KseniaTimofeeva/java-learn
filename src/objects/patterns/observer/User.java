package objects.patterns.observer;

/**
 * Created by ksenia on 24.04.2017.
 */
public class User {



    public static void main(String[] args) {
        Sensor sensor = new Sensor();

//        to green
        sensor.subscribe(new Listener() {
            @Override
            public void publish(int level) {
                if (level == 0) {
                    System.out.println("Green level");
                }
            }
        });

//        to yellow
        sensor.subscribe(new Listener() {
            @Override
            public void publish(int level) {
                if (level == 1) {
                    System.out.println("Yellow level");
                }
            }
        });

//        to red
        sensor.subscribe(new Listener() {
            @Override
            public void publish(int level) {
                if (level == 2) {
                    System.out.println("Red level");
                }
            }
        });

        sensor.temperature();
    }
}
