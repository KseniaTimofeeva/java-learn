package objects.multithreading.pizzeria.restaurant;

import java.util.Random;

/**
 * Created by ksenia on 04.05.2017.
 */
public class Restaurant {
    Waiter waiter;
    Chef chef;
    Servant servant;
    Order orders = new Order();
    ReadyOrder readyOrders = new ReadyOrder();

    public Restaurant() {
        chef = new Chef(this);
        waiter = new Waiter(this);
        servant = new Servant(this);
    }

    private void startWorkers() {
        Thread waiterThr = new Thread(waiter, "waiter");
        Thread chefThr = new Thread(chef, "chef");
        Thread servantThr = new Thread(servant, "servant");
        waiterThr.start();
        chefThr.start();
        servantThr.start();
    }

    private void startRestaurant() {
        synchronized (waiter) {
            System.out.println("Order:");
            for (Order.SubOrder subOrder : orders.ordersQueue) {
                System.out.println(subOrder.getDish() + " " + subOrder.getQty());
            }

            orders.readyToWaiter = true;
            waiter.notifyAll();
        }

        try {
            synchronized (this) {
                System.out.println("Visitor is waiting");
                while (!readyOrders.readyToVisitor) {
                    wait();
                }
            }

            readyOrders.readyToVisitor = false;
            Menu dish;
            System.out.println("Ready order:");
            while ((dish = readyOrders.getOrder()) != null) {
                System.out.println(dish);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        restaurant.startWorkers();

        Random rnd = new Random();
        synchronized (restaurant) {
            restaurant.orders.addOrder(Menu.randomValue(), rnd.nextInt(5) + 1);
            restaurant.orders.addOrder(Menu.randomValue(), rnd.nextInt(5) + 1);
            restaurant.orders.addOrder(Menu.randomValue(), rnd.nextInt(5) + 1);

        }
        restaurant.startRestaurant();

        synchronized (restaurant) {
            restaurant.orders.addOrder(Menu.randomValue(), rnd.nextInt(5) + 1);
            restaurant.orders.addOrder(Menu.randomValue(), rnd.nextInt(5) + 1);
            restaurant.orders.addOrder(Menu.randomValue(), rnd.nextInt(5) + 1);

        }
        restaurant.startRestaurant();

    }
}

