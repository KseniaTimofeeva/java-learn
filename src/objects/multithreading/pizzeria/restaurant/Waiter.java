package objects.multithreading.pizzeria.restaurant;

/**
 * Created by ksenia on 04.05.2017.
 */
public class Waiter extends Thread {

    private Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {

                synchronized (this) {
                    while (!restaurant.orders.readyToWaiter && !restaurant.readyOrders.readyToWaiter) {
                        System.out.println("Waiter is waiting");
                        wait();
                    }
                }

                if (restaurant.orders.readyToWaiter) {
                    synchronized (restaurant.chef) {
                        System.out.println("Waiter is transferring to chef");
                        restaurant.orders.readyToWaiter = false;
                        restaurant.orders.readyToChef = true;
                        restaurant.chef.notifyAll();
                    }
                }

                if (restaurant.readyOrders.readyToWaiter) {
                    synchronized (restaurant) {
                        System.out.println("Waiter brings the ready order to visitor");
                        restaurant.readyOrders.readyToWaiter = false;
                        restaurant.readyOrders.readyToVisitor = true;
                        restaurant.notifyAll();
                    }
                }


            }
        } catch (InterruptedException e) {
            interrupt();
            e.printStackTrace();
        }
    }
}
