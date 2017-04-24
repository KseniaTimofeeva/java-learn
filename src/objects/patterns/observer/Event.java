package objects.patterns.observer;

/**
 * Created by ksenia on 25.04.2017.
 */
public class Event {
    private double temperature;

    public Event(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
