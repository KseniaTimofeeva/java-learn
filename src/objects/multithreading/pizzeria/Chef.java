package objects.multithreading.pizzeria;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ksenia on 02.05.2017.
 */
public class Chef extends Thread {
    private List<SubOrder> orders;
    private Waiter waiter;
    private Servant servant;
    private boolean waitingForServing;

    public Chef() {
        orders = new LinkedList<>();
        waitingForServing = false;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public void setServant(Servant servant) {
        this.servant = servant;
    }


    public boolean isWaitingForServing() {
        return waitingForServing;
    }

    public synchronized void setWaitingForServing(boolean waitingForServing) {
        this.waitingForServing = waitingForServing;
    }


    public synchronized void addOrderToQueue(SubOrder subOrder) {
        orders.add(subOrder);
    }

    @Override
    public void run() {
        System.out.println("chef started");
        while (!isInterrupted()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }


            System.out.println("chef woke up");
            if (waiter.isReadyForCooking()) {
                synchronized (servant) {
                    Iterator iter = orders.iterator();
                    while (iter.hasNext()) {
                        SubOrder subOrder = (SubOrder) iter.next();
                        for (int i = 0; i < subOrder.getQty(); i++) {
                            Food food = new Food(subOrder.getDish());
                            servant.addFoodForServing(food);
                        }
//                    iter.remove();
                    }
                    setWaitingForServing(true);
                    servant.notify();
                }
                waiter.setReadyForCooking(false);
            }


        }
    }
}
