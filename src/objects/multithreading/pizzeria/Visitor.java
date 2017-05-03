package objects.multithreading.pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ksenia on 02.05.2017.
 */
public class Visitor extends Thread {
    private List<SubOrder> order;
    private boolean readyToOrder;
    private Waiter waiter;
    private List<Food> readyFood;

    public Visitor(int table, Waiter waiter) {
        order = new ArrayList<>();
        readyToOrder = false;
        this.waiter = waiter;
        readyFood = new ArrayList<>();
    }

    public List<SubOrder> getOrder() {
        return order;
    }

    public boolean isReadyToOrder() {
        return readyToOrder;
    }

    public void addSubOrder(Menu dish, int qty) {
        order.add(new SubOrder(dish, qty));
    }

    public synchronized void setReadyFood(Food readyFood) {
        this.readyFood.add(readyFood);
    }

    @Override
    public void run() {
        System.out.println("visitor started");
        Random rnd = new Random();

        waiter.addClient(rnd.nextInt(10), this);

        addSubOrder(Menu.randomValue(), rnd.nextInt(4) + 1);
        addSubOrder(Menu.randomValue(), rnd.nextInt(4) + 1);
        addSubOrder(Menu.randomValue(), rnd.nextInt(4) + 1);

        synchronized (waiter) {
            readyToOrder = true;
            waiter.notifyAll();
        }

        synchronized (this) {
            try {
                this.wait();
                System.out.println("visitor woke up");
                if (waiter.isReadyFood()) {
                    System.out.println("Order:");
                    for (SubOrder sub : order) {
                        System.out.println(sub.getDish() + " " + sub.getQty());
                    }
                    System.out.println("Ready food:");
                    for (Food food : readyFood) {
                        System.out.println(food.getDish());
                    }
                    waiter.setReadyFood(false);
                }

            } catch (InterruptedException e) {
                interrupt();
                e.printStackTrace();
            }
        }
    }
}
