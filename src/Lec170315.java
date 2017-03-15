import java.util.*;

/**
 * Created by ksenia on 15.03.2017.
 */
public class Lec170315 {
    public static void main(String[] args) {
        ex1();
        ex2();
        ex3();
        ex4();
        ex5();
        ex6();
        ex7();
        ex8();
        ex9();
        ex10();
        ex11();
        ex12();
    }

    //Условный оператор
    //(1)
    static  void ex1() {
        int n = 6;
        String type = n % 2 == 0 ? "четное" : "нечетное";
        System.out.println("Число " + n + " " + type);
        System.out.println();

    }

    //(2)
    static void ex2() {
        double n1 = 8.5;
        double n2 = 11.45;

        double type = (Math.abs(n1 - 10)) > (Math.abs(n2 - 10)) ? n2 : n1;
        System.out.println("Из чисел " + n1 + " и " + n2 + " ближайшее к 10: " + type);
        System.out.println();
    }

    //(3)
    static void ex3() {
        double a = 1.2;
        double b = -70.5;
        double c = 600;

        double d = Math.pow(b, 2) - 4 * a * c;
        String result = d >= 0 ? "x1 = " + ((-b + Math.sqrt(d)) / 2 * a) + " x2 = " + ((-b - Math.sqrt(d)) / 2 * a) : " Корней нет";
        System.out.println(result);
        System.out.println();
    }

    //Ветвление
    //(1)
    static void ex4() {
        int min = 5; //включительно
        int max = 155; //включительно
        int x = (int) (Math.random() * (max - min + 1) + 5);
        if ((x > 25) && (x < 100)) {
            System.out.println("Число " + x + " содержится в интервале (25,100)");
        } else {
            System.out.println("Число " + x + " не содержится в интервале (25,100)");
        }
        System.out.println();
    }

    //(2)
    static void ex5() {
        int min = 100; //включительно
        int max = 999; //включительно
        int x = (int) (Math.random() * (max - min + 1) + 100);
        int number = x;
        int result1 = 0;
        int result2 = 0;
        for (int i = 0; i < 3; i++) {
            result2 = number % 10;
            if (result2 > result1) {
                result1 = result2;
            }
            number = number / 10;
        }
        System.out.println("В числе " + x + " наибольшая цифра " + result1);
        System.out.println();
    }

    //3)
    static void ex6() {
        int a = 3;
        int b = 9;
        int c = -1;
        int x = 0;
        System.out.println("Числа в переменных a, b и c: " + a + " " + b + " " + c);

        for (int i = 0; i < 2; i++) {
            if (a > b) {
                x = a;
                a = b;
                b = x;
            } else if (b > c) {
                x = b;
                b = c;
                c = x;
            }
        }
        System.out.println("Возрастающая последовательность: " + a + " " + b + " " + c);
        System.out. println();
    }

    //(4)
    static void ex7 () {
        Random rnd = new Random();
        int n = rnd.nextInt(28801);

        int hour = (int) (n / 3600);
        System.out.println(n);
        String descrip1 = "";
        String descrip2 = "Осталось";

        switch (hour) {
            case 1:
                descrip1 = "час";
                descrip2 = "Остался";
                break;
            case 2:
            case 3:
            case 4:
                descrip1 = "часа";
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                descrip1 = "часов";
                break;
            default:
                descrip1 = "менее часа";
        }
        if (hour != 0) {
            System.out.println(descrip2 + " " + hour + " " + descrip1);
        } else {
            System.out.println(descrip2 + " " + descrip1);
        }
        System.out.println();
    }

    //Циклы
    //(1)
    static void ex8() {
        for (int i = 1000; i < 10000; i+=300) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }

    //(2)
    static void ex9() {
        for (int i = 0, j = 1; i < 55; i++, j += 2) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println();
    }

    //(3)
    static void ex10() {
        int i = 90;
        while (i >= 0) {
            System.out.print(i + " ");
            i -= 5;
        }
        System.out.println();
        System.out.println();
    }

    //(4)
    static void ex11() {
        for (int i = 0, j = 2; i < 20; i++, j*=2) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println();
    }

    //(5)
    static void ex12() {
        int n;
        int factorial = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите натуральное число: ");
        if (sc.hasNext()) {
            n = sc.nextInt();
        } else {
            n = 10;
        }

        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);
        System.out.println();

    }
}
