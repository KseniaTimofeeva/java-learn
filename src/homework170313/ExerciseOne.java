package homework170313;

/**
 * Created by ksenia on 14.03.2017.
 */
public class ExerciseOne {
    public static void main(String[] args) {
        int q = 43;
        int w = 6;

        if (w != 0) {
            int a = q / w;
            int b = q % w;
            System.out.println(q + " / " + w + " = " + a + " и " + b + " в остатке");
        } else {
            System.out.println("Нельзя делить на 0!");
        }

    }
}
