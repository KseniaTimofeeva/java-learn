package objects.patterns.observer;

import objects.reflection.toString.StringUtils;

/**
 * Created by ksenia on 24.04.2017.
 */
public class Station {

    //0-выход на рабочий режим
    //1-зеленый
    //2- желтый
    //3-красный

    private Sensor sensor;
    private int currentLevel;

    private int[] a = {0,1,2};


    public Station() {
        sensor = new Sensor();
        currentLevel = 0;
    }

    public void checkLevel() throws InterruptedException {

        sensor.subscribe(new Listener() {
            @Override
            public void publish(Event event) {
                if (event.getTemperature() >= 15 && event.getTemperature() < 30) {
                    if (currentLevel != 1) {
                        System.out.println("Green level");
                        currentLevel = 1;
                    }
                }
            }
        });

        sensor.subscribe(new Listener() {
            @Override
            public void publish(Event event) {
                if (event.getTemperature() >= 30 && event.getTemperature() < 45) {
                    if (currentLevel != 2) {
                        System.out.println("Yellow level");
                        currentLevel = 2;
                    }
                }
            }
        });

        sensor.subscribe(new Listener() {
            @Override
            public void publish(Event event) {
                if (event.getTemperature() >= 45) {
                    if (currentLevel != 3) {
                        System.out.println("Red level");
                        currentLevel = 3;
                    }
                }
            }
        });

        sensor.changeTemperature();
    }


    public static void main(String[] args) throws InterruptedException {

        Station station = new Station();
        station.checkLevel();
        System.out.println(StringUtils.toString(station));
    }
}
