package objects.shop;

/**
 * Created by ksenia on 02.04.2017.
 */
public class MenuHolder {
    public static Menu mainMenu;
    public static UserIdInputMenu userIdInputMenu;
    public static ShopCommandMenu shopCommandMenu;
    public static CartMenu cartMenu;

    public MenuHolder(Shop shop) {
        mainMenu = new MainMenu(shop);
        userIdInputMenu = new UserIdInputMenu(shop);
        shopCommandMenu = new ShopCommandMenu(shop);
        cartMenu = new CartMenu(shop);
    }
}
