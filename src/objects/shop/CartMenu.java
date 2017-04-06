package objects.shop;

import java.util.Scanner;

/**
 * Created by ksenia on 03.04.2017.
 */
public class CartMenu implements Menu {
Shop shop;

    public CartMenu(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String print() {
        String s = "";
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nМ Е Н Ю:\n\n");
        stringBuilder.append("delete id qty - удалить товар id в количестве qty из корзины\n");
        stringBuilder.append("buy totalSum - оплатить товар (totalSum - стоимость заказа)\n");
        stringBuilder.append("0 - выйти из магазина\n");
        stringBuilder.append("1 - продолжить покупки / показать товар\n");
        System.out.println(stringBuilder);

        if (sc.hasNextLine()) {
            s = sc.nextLine();
        }
        return s;
    }

    @Override
    public void action(String s) {
        boolean b = Utils.checkRegExp(s, "\\d");
        if (b) {
            switch (Integer.parseInt(s)) {
                case 0:
                    MenuHolder.mainMenu.action(MenuHolder.mainMenu.print());
                    break;
                case 1:
                    shop.stockOutput();
                    MenuHolder.shopCommandMenu.action(MenuHolder.shopCommandMenu.print());
                    break;
                default:
                    action(print());
            }
        } else {
            b = Utils.checkRegExp(s, "delete\\s(\\d)+\\s(\\d)+");
            if (b) {
                String[] commamd = s.split(" ");
                boolean delete = shop.deleteProductFromCart(Integer.parseInt(commamd[1]), Integer.parseInt(commamd[2]));
                if (delete) {
                    System.out.println("Изменения внесены");
                    shop.showCart();
                }
                action(print());
            } else {
                b = Utils.checkRegExp(s, "buy\\s(\\d)+(\\.)?(\\d)+");
                if (b) {
                    String[] commamd = s.split(" ");
                    boolean buy = shop.buy(Integer.parseInt(commamd[1]));
                    if (buy) {
                        System.out.println("Спасибо за покупку!");
                        shop.stockOutput();
                        MenuHolder.shopCommandMenu.action(MenuHolder.shopCommandMenu.print());
                    } else {
                        action(print());
                    }
                } else {
                    action(print());
                }
            }
        }
    }
}
