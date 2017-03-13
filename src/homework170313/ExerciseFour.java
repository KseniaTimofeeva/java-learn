package homework170313;

/**
 * Created by ksenia on 14.03.2017.
 */
public class ExerciseFour {
    public static void main(String[] args) {
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
    }
}
