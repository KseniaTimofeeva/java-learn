package objects.multithreading.pizzeria.restaurant;

import objects.multithreading.pizzeria.restaurant.Menu;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ksenia on 02.05.2017.
 */
public class Order {

    List<SubOrder> ordersQueue;
    boolean readyToWaiter = false;
    boolean readyToChef = false;

    public Order() {
        ordersQueue = new LinkedList<>();
    }

    public void addOrder(Menu dish, int qty) {
        ordersQueue.add(new SubOrder(dish, qty));
    }

    public SubOrder getOrder() {
        if (ordersQueue.size() == 0) {
            return null;
        }
        return ordersQueue.remove(0);
    }


    public class SubOrder {
        private Menu dish;
        private int qty;

        public SubOrder(Menu dish, int qty) {
            this.dish = dish;
            this.qty = qty;
        }

        public Menu getDish() {
            return dish;
        }

        public int getQty() {
            return qty;
        }
    }
}
