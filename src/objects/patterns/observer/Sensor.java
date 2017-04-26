package objects.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksenia on 24.04.2017.
 */
public class Sensor {

    private List<Listener> listeners = new ArrayList<>();

    public void subscribe(Listener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(Event event) {
        for (Listener listener : listeners) {
            listener.publish(event);
        }
    }

    private void temperature(double t) {
        System.out.println("t = " + Math.round(t));
        notifyListeners(new Event(t));
    }

    public void changeTemperature() throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            temperature(Math.sin(i) * i + 2 * i);
            Thread.sleep(500);
        }
    }

}
