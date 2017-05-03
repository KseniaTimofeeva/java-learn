package objects.multithreading.pizzeria;

import java.util.Random;

/**
 * Created by ksenia on 02.05.2017.
 */
public enum Menu {
    FISH, MEAT, SALAD, COFFEE, TEA, PASTE, JUICE, APPLE, SOUP, PIZZA;

    public static Menu randomValue() {
        Random rnd = new Random();
        Menu[] values = Menu.values();
        return values[rnd.nextInt(values.length)];
    }
}
