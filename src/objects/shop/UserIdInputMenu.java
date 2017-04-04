package objects.shop;

import java.util.Scanner;

/**
 * Created by ksenia on 02.04.2017.
 */
public class UserIdInputMenu implements Menu {
    Shop shop;

    public UserIdInputMenu(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String print() {
        int n = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nВведите id пользователя\n");
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        }
        return n + "";
    }

    @Override
    public void action(String s) {
        int n = Integer.parseInt(s);
        if (n < 0 || n >= shop.getNextUserId()) {
            System.out.println("\n-------------------------------------");
            System.out.println("Пользователя с таким id не существует");
            System.out.println("-------------------------------------");
            MenuHolder.mainMenu.action(MenuHolder.mainMenu.print());
        } else {
            System.out.println("---------------------------------");
            System.out.println("Здравствуйте, пользователь " + n + "!");
            System.out.println("---------------------------------");
            shop.setCurrentUserId(n);
            shop.stockOutput();
            MenuHolder.shopCommandMenu.action(MenuHolder.shopCommandMenu.print());
        }
    }
}
