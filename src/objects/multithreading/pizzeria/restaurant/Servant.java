package objects.multithreading.pizzeria.restaurant;

/**
 * Created by ksenia on 04.05.2017.
 */
public class Servant extends Thread {

    private Restaurant restaurant;

    public Servant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {

                synchronized (this) {
                    while (!restaurant.readyOrders.readyToServant) {
                        System.out.println("Servant is waiting");
                        wait();
                    }
                }

                synchronized (restaurant.waiter) {
                    System.out.println("Servant is working");
                    restaurant.readyOrders.readyToServant = false;
                    restaurant.readyOrders.readyToWaiter = true;
                    restaurant.waiter.notifyAll();
                }
            }
        }  catch (InterruptedException e) {
            interrupt();
            e.printStackTrace();
        }
    }

}
