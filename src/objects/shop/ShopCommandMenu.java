package objects.shop;

import java.util.Scanner;

/**
 * Created by ksenia on 02.04.2017.
 */
public class ShopCommandMenu implements Menu {
    Shop shop;

    public ShopCommandMenu(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String print() {
        String s = "";
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nМ Е Н Ю:\n\n");
        stringBuilder.append("add id qty - добавить товар id в количестве qty в корзину\n");
        stringBuilder.append("0 - выйти из магазина\n");
        stringBuilder.append("1 - показать корзину/купить\n");
        System.out.println(stringBuilder);

        int n = -1;
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
                    shop.showCart();
                    MenuHolder.cartMenu.action(MenuHolder.cartMenu.print());
                    break;
                default:
                    print();
            }
        } else {
            b = Utils.checkRegExp(s, "add\\s(\\d)+\\s(\\d)+");
            if (b) {
                String[] commamd = s.split(" ");
                boolean add = shop.addProductToCart(Integer.parseInt(commamd[1]), Integer.parseInt(commamd[2]));
                if (add) {
                    System.out.println("------------------------");
                    System.out.println("Товар добавлен в корзину");
                    System.out.println("------------------------");
                }
            }
            print();
        }
    }
}
