package objects.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksenia on 24.04.2017.
 */
public class Sensor {
    int t;
    int lastt;

    private List<Listener> listeners = new ArrayList<>();

    private int changeTemp(int t, int value, boolean incr, int... points) {
        if (incr) {
            while (t < value) {
                lastt = t;
                t++;
                for (int i = 0, k = 0; i < points.length / 3; i++, k += 3) {
                    if (lastt == points[k] && t == points[k + 1]) {
                        notifyListeners(points[k + 2]);
                    }
                }
            }
        } else {
            while (t > value) {
                lastt = t;
                t--;
                for (int i = 0, k = 0; i < points.length / 3; i++, k += 3) {
                    if (lastt == points[k] && t == points[k + 1]) {
                        notifyListeners(points[k + 2]);
                    }
                }
            }
        }
        return t;
    }

    public void temperature() {
        t = 0;
        lastt = 0;
        t = changeTemp(t, 1300, true, 1199, 1200, 1);
        t = changeTemp(t, 1100, false, 1200, 1199, 0);
        t = changeTemp(t, 1700, true, 1199, 1200, 1, 1599, 1600, 2);
        t = changeTemp(t, 1500, false, 1600, 1599, 1);
    }

    public void subscribe(Listener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(int level) {
        for (Listener listener : listeners) {
            listener.publish(level);
        }
    }

}
