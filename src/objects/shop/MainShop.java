package objects.shop;

/**
 * Created by ksenia on 02.04.2017.
 */
public class MainShop {
    public static void main(String[] args) {
        Shop shop = new Shop();
        MenuHolder menuHolder = new MenuHolder(shop);

        for (int i = 0; i < 10; i++) {
            shop.addProduct(new Product("конфета " + i, 10.0), 10);
        }

        menuHolder.mainMenu.action(menuHolder.mainMenu.print());

    }
}
