package homework170313;

/**
 * Created by ksenia on 14.03.2017.
 */
public class ExerciseThree {
    public static void main(String[] args) {
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
    }
}
