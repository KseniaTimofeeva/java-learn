package objects.multithreading.pizzeria;

/**
 * Created by ksenia on 02.05.2017.
 */
public class RestaurantMain {

    private Waiter waiter;
    private Chef chef;
    private Servant servant;
    private boolean newVisitor;

    public RestaurantMain() {
        chef = new Chef();
        waiter = new Waiter();
        servant = new Servant();
        waiter.setChef(chef);
        waiter.setServant(servant);
        chef.setWaiter(waiter);
        chef.setServant(servant);
        servant.setChef(chef);
        servant.setWaiter(waiter);
    }

    private void startWorkers() {
        Thread waiterThr = new Thread(waiter, "waiter");
        Thread chefThr = new Thread(chef, "chef");
        Thread servantThr = new Thread(servant, "servant");
        waiterThr.start();
        chefThr.start();
        servantThr.start();
    }

    public static void main(String[] args) {
        RestaurantMain restaurantMain = new RestaurantMain();
        restaurantMain.startWorkers();

        Thread visitorThr0 = new Thread(new Visitor(1, restaurantMain.waiter), "visitor");
        visitorThr0.start();

    }
}
