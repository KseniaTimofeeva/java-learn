package primitives;

/**
 * Created by ksenia on 14.03.2017.
 */

//13.03.2017
public class Lec170313 {
    public static void main(String[] args) {
        exOne();
        exTwo();
        exThree();
        exFour();
        exFive();
    }

    static void exOne() {
        int q = 43;
        int w = 6;

        if (w != 0) {
            int a = q / w;
            int b = q % w;
            System.out.println(q + " / " + w + " = " + a + " и " + b + " в остатке");
            System.out.println();
        } else {
            System.out.println("Нельзя делить на 0!");
            System.out.println();
        }
    }

    static void exTwo() {
        int n = 85;
        int a = n / 10;
        int b = n % 10;

        System.out.println("Сумма цифр числа " + n + " = " + (a + b));
        System.out.println();
    }

    static void exThree() {
        double n = 55.95;
        int nWhole = (int) n;
        double nReal = n - nWhole;
        int result = 0;

        if ((nReal * 10) < 5) {
            result = nWhole;
        } else {
            result = nWhole + 1;
        }

        System.out.println("Число " + n + " округляем до " + result);
        System.out.println();
    }

    static void exFour() {
        int n = 234;
        int number = n;
        int ost = 0;
        int sum = 0;

        String str = Integer.toString(n);
        for (int i = 0; i < str.length(); i++) {
            ost = number % 10;
            number = number / 10;
            sum += ost;
        }
        System.out.println("Сумма цифр числа " + n + " = " + sum);
        System.out.println();
    }

    static void exFive() {
        int n = 25;
        boolean result = true;
        String description;

        if ((n & 1) == 1) {
            result = false;
            description = "нечетное";
        } else {
            result = true;
            description = "четное";
        }
        System.out.println("Число " + n + " " + description + " - " + result);
        System.out.println();
    }
}
