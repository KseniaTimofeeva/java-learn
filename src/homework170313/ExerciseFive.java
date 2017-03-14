package homework170313;

/**
 * Created by ksenia on 14.03.2017.
 */
public class ExerciseFive {
    public static void main(String[] args) {
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
    }
}
