package objects.multithreading.pizzeria;

/**
 * Created by ksenia on 02.05.2017.
 */
public class Food {
    private Menu dish;
    private boolean isServed;

    public Food(Menu dish) {
        this.dish = dish;
        isServed = false;
    }

    public synchronized void isServed() {
        isServed = true;
    }

    public Menu getDish() {
        return dish;
    }
}
