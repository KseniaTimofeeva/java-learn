package objects.shop;

import java.util.Scanner;
/**
 * Created by ksenia on 02.04.2017.
 */
public class MainMenu implements Menu {
    private Shop shop;

    MainMenu(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String print() {
        int n = -1;
        Scanner sc = new Scanner(System.in);
        do {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\nМ Е Н Ю:\n\n");
            stringBuilder.append("(нажмите соответствующую клавишу для выбора пункта меню)\n");
            stringBuilder.append("0 - выход\n");
            stringBuilder.append("1 - ввод id пользователя\n");
            stringBuilder.append("2 - регистрация нового пользователя\n");
            stringBuilder.append("3 - показать все транзакции\n");
            System.out.println(stringBuilder);

            if (sc.hasNextInt()) {
                n = sc.nextInt();
            }
        } while (n < 0 || n > 2);
        return n + "";
    }

    @Override
    public void action(String n) {
        switch (Integer.parseInt(n)) {
            case 0:
                System.exit(-1);
            case 1:
                MenuHolder.userIdInputMenu.action(MenuHolder.userIdInputMenu.print());
                break;
            case 2:
                int yourId = shop.addUser();
                System.out.println("---------------");
                System.out.println("Ваш id: " + yourId);
                System.out.println("---------------");
                shop.stockOutput();
                MenuHolder.shopCommandMenu.action(MenuHolder.shopCommandMenu.print());
                break;
            case 3:

                break;
        }
    }
}
