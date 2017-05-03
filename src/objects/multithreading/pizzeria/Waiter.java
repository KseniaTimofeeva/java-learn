package objects.multithreading.pizzeria;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ksenia on 02.05.2017.
 */
public class Waiter extends Thread {
    private Map<Integer, List<SubOrder>> orders;
    private Map<Integer, Visitor> visitors;
    private Chef chef;
    private boolean readyForCooking;
    private Servant servant;
    private List<Food> foodForVisitor;
    private boolean readyFood;


    public Waiter() {
        orders = new HashMap<>();
        visitors = new HashMap<>();
        readyForCooking = false;
        readyFood = false;
        foodForVisitor = new LinkedList<>();
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public void setServant(Servant servant) {
        this.servant = servant;
    }


    public synchronized void setReadyForCooking(boolean readyForCooking) {
        this.readyForCooking = readyForCooking;
    }

    public boolean isReadyForCooking() {
        return readyForCooking;
    }


    public synchronized void setReadyFood(boolean readyFood) {
        this.readyFood = readyFood;
    }

    public boolean isReadyFood() {
        return readyFood;
    }


    public void addOrder(int table, List<SubOrder> visitorOrder) {
        orders.putIfAbsent(table, visitorOrder);
    }

    public synchronized void addClient(int table, Visitor visitor) {
        visitors.putIfAbsent(table, visitor);
    }

    public synchronized void addFoodForVisitor(Food food) {
        foodForVisitor.add(food);
    }

    @Override
    public void run() {
        System.out.println("waiter started");
        while (!isInterrupted()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println("waiter woke up");
            if (servant.isFinish()) {

                synchronized (this) {

                    Visitor visitor = null;
                    Iterator iter = foodForVisitor.iterator();
                    while (iter.hasNext()) {
                        Food food = (Food) iter.next();
                        for (Map.Entry<Integer, List<SubOrder>> entry : orders.entrySet()) {
                            for (SubOrder subOrder : entry.getValue()) {
                                if (food.getDish().equals(subOrder.getDish())) {
                                    visitor = visitors.get(entry.getKey());
                                    visitor.setReadyFood(food); //поставили на стол готовое
//                                    subOrder.setQty(subOrder.getQty() - 1); //убрали из заказа то, что отдаем на стол

                                }
                            }

                        }
//                    iter.remove();
                    }
                    setReadyFood(true);
                    if (visitor != null) {
                        synchronized (visitor) {
                            visitor.notify();
                        }
                    }
                }
                servant.setFinish(false);
            }

            for (Map.Entry<Integer, Visitor> entry : visitors.entrySet()) {
                if (entry.getValue().isReadyToOrder()) {
                    List<SubOrder> visitorOrder = entry.getValue().getOrder();
                    addOrder(entry.getKey(), visitorOrder);

                    synchronized (chef) {
                        for (SubOrder subOrder : visitorOrder) {
                            chef.addOrderToQueue(subOrder);
                        }
                        setReadyForCooking(true);

                        chef.notify();
                    }
                }
            }


        }
    }
}
