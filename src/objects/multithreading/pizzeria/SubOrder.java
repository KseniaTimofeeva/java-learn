package objects.multithreading.pizzeria;

/**
 * Created by ksenia on 02.05.2017.
 */
public class SubOrder {
    private Menu dish;
    private int qty;

    public SubOrder(Menu dish, int qty) {
        this.dish = dish;
        this.qty = qty;
    }

    public Menu getDish() {
        return dish;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
