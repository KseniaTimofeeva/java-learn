package objects.multithreading.pizzeria;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ksenia on 02.05.2017.
 */
public class Servant extends Thread {
    private Chef chef;
    private Waiter waiter;
    private List<Food> foodForServing;
    private boolean finish;

    public void setChef(Chef chef) {
        this.chef = chef;
        foodForServing = new LinkedList<>();
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }


    public synchronized void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isFinish() {
        return finish;
    }


    public synchronized void addFoodForServing(Food food) {
        foodForServing.add(food);
    }

    @Override
    public void run() {
        System.out.println("servant started");
        while (!isInterrupted()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }


            System.out.println("servant woke up");
            if (chef.isWaitingForServing()) {

                synchronized (this) {

                    Iterator iter = foodForServing.iterator();
                    while (iter.hasNext()) {
                        Food food = (Food) iter.next();
                        food.isServed();
                        waiter.addFoodForVisitor(food);

//                    iter.remove();
                    }
                }
                synchronized (waiter) {
                    setFinish(true);
                    waiter.notify();
                }
                chef.setWaitingForServing(false);
            }

        }
    }
}
