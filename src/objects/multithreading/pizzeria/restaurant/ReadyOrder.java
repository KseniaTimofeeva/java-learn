package objects.multithreading.pizzeria.restaurant;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ksenia on 04.05.2017.
 */
public class ReadyOrder {
    private List<Menu> readyOrders;
    boolean readyToServant = false;
    boolean readyToWaiter = false;
    boolean readyToVisitor = false;

    public ReadyOrder() {
        readyOrders = new LinkedList<>();
    }

    public void addReadyOrder(Menu dish) {
        readyOrders.add(dish);
    }

    public Menu getOrder() {
        if (readyOrders.size() == 0) {
            return null;
        }
        return readyOrders.remove(0);
    }

}
