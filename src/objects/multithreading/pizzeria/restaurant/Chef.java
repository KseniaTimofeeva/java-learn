package objects.multithreading.pizzeria.restaurant;

/**
 * Created by ksenia on 04.05.2017.
 */
public class Chef extends Thread {

    private Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {

                synchronized (this) {
                    while (!restaurant.orders.readyToChef) {
                        System.out.println("Chef is waiting");
                        wait();
                    }
                }

                synchronized (restaurant.servant) {
                    Order.SubOrder subOrder;
                    while ((subOrder = restaurant.orders.getOrder()) != null) {
                        for (int i = 0; i < subOrder.getQty(); i++) {
                            restaurant.readyOrders.addReadyOrder(subOrder.getDish());
                        }
                    }
                    System.out.println("Chef is cooking");
                    restaurant.orders.readyToChef = false;
                    restaurant.readyOrders.readyToServant = true;
                    restaurant.servant.notifyAll();
                }

            }
        } catch (InterruptedException e) {
            interrupt();
            e.printStackTrace();
        }
    }
}
