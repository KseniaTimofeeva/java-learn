package primitives;

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
       // ex12();
       // ex13();
        //ex14();
        ex15();
        //ex16();
        ex17();
        ex18();
        ex19();
        ex20();
    }

    //Условный оператор
    //(1)
    //четное-нечетное
    static  void ex1() {
        int n = 6;
        String type = n % 2 == 0 ? "четное" : "нечетное";
        System.out.println("Число " + n + " " + type);
        System.out.println();

    }

    //(2)
    //число ближайшее к 10
    static void ex2() {
        double n1 = 8.5;
        double n2 = 11.45;

        double type = (Math.abs(n1 - 10)) > (Math.abs(n2 - 10)) ? n2 : n1;
        System.out.println("Из чисел " + n1 + " и " + n2 + " ближайшее к 10: " + type);
        System.out.println();
    }

    //(3)
    //корни квадратного уравнения
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
    //наибольшая цифра в числе
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

    //(3)
    //сортировка
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
    //часы до конца рабочего дня
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
    //факториал
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

    //(6)
    //делители
    static void ex13() {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите натуральное число: ");
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        } else {
            n = 224;
        }
        System.out.println("Делители:");
        for (int i = 1; i <= (n / 2); i++) {
            if (n % i == 0) {
                System.out.print(" " + i);
            }
        }
        System.out.println(" " + n);
        System.out.println();
    }

    //(7)
    //является ли число простым
    static void ex14() {
        int n;
        boolean result = false; //false - составное, true - простое
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите натуральное число: ");
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        } else {
            n = 224;
        }
        if (n == 2) result = true;
        else {
            result = true;
            for (int i = 2; i <= Math.round(Math.sqrt(n)); i++) {
                if (n % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        if (result) {
            System.out.println("Число " + n + " простое");
        } else {
            System.out.println("Число " + n + " составное");
        }
        System.out.println();
    }


    //(8)
    //последовательность Фибоначчи
    static void ex15() {
        int number = 0;
        int nextNumber = 1;
        int sum = 0;
        int k = 11;
        System.out.print("Первые " + k + " членов последовательности Фибоначчи:");
        for (int i = 0; i < k; i++) {
            sum = number + nextNumber;
            number = nextNumber;
            nextNumber = sum;
            System.out.print(" " + number);
        }
        System.out.println();
        System.out.println();
    }

    //(9)
    //сумма цифр числа
    static void ex16() {
        int n;
        int whole = 0;
        int remainder = 0;
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите натуральное число: ");
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        } else {
            n = 555;
        }
        int nextNumber = n;
        do {
            whole = nextNumber / 10;
            remainder = nextNumber % 10;
            sum += remainder;
            nextNumber = whole;
        } while (nextNumber != 0);
        System.out.println("Сумма цифр " + sum);
        System.out.println();
    }


    //(10)
    //счастливые билеты
    static void ex17() {
        int qty = 0;
        /*int min = 1;
        int max = 999999;
        int sum1;
        int sum2;
        int number;
        for (int i = 1001; i <= 999999; i++) { //счастливые билеты начинаются от 1001
            sum1 = 0;
            sum2 = 0;
            number = i;
            for (int j = 0; j < 6; j++) {
                if (j < 3) {
                    sum1 += number % 10;
                } else {
                    sum2 += number % 10;
                }
                number = number / 10;
            }
            if (sum1 == sum2) {
                qty++;
            }
        }*/

        int[] combinations = new int[28]; //массив - сколкьо раз встречается соотв сумма цифр
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    combinations[i + j + k]++;
                }
            }
        }
        for (int i = 1; i < 28; i++) { //начинаем с ячейки 1, т.к. билета 000000 не существует
            qty += Math.pow(combinations[i], 2);
        }
        System.out.println("В одном рулоне " + qty + " счастливых билетов");
        System.out.println();
    }


    //(11)
    //бракованные таблички на складе
    static void ex18() {
        int defect = 0;
        for (int i = 1; i <= 50000 ; i++) {
            String s = Integer.toString(i);
            if (s.indexOf('2') != -1) {
                defect++;
            }
        }
        System.out.println("Необходимо заменить " + defect + " табличек");
        System.out.println();

    }


    //(12)
    //симметричная комбинация на часах
    static void ex19() {
        int qty = 24;
        for (int i = 0; i <= 1; i++) {
            for (int j = 6; j <= 9; j++) {
                qty--;
            }
        }
        System.out.println("Симметричная комбинация показывается " + qty + " раз за сутки");
        System.out.println();
    }


    //(13)
    //армия
    static void ex20() {
        int defect = 0;
        for (int i = 1; i < 99999; i++) {
            String s = Integer.toString(i);
            if (s.contains("4") || s.contains("13")) {
                defect++;
            }
        }
        System.out.println("Необходимо исключить " + defect + " номеров");
        System.out.println();

    }

}
