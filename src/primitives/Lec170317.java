package primitives;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by ksenia on 17.03.2017.
 */
public class Lec170317 {
    public static void main(String[] args) {
        String str = "texttexttext";
        //int key = 12;
        String key = "passw12";
        ex1(str.getBytes(), key.getBytes());

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
        ex13();
        //ex14();
        ex15();
        //ex16();
        ex17();
        ex18();
        ex19();
        ex20();
        ex21();
    }

    //(1)криптография
    static void ex1(byte[] text, byte[] key) {
        System.out.println(new String(text));
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < text.length; i += key.length) {
                for (int j = 0; (i + j) < text.length && j < key.length; j++) {
                    text[i + j] ^= key[j];
                }
            }
            System.out.println(new String(text));
        }
        System.out.println();

    }

    //метод для сортировки массива методом пузырька
    static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int a = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = a;
                }
            }
        }
    }

    //создание и заполнение одномерного массива случайных чисел
    static int[] makeArray(int qty, int minBound, int maxBound) {   //qty - кол-во элементов
        int[] array = new int[qty];
        Random rnd = new Random();                                  //minBound - min граница интервала сл.чисел
        for (int i = 0; i < array.length; i++) {                    //maxBound - max граница интервала сл.чисел
            array[i] = rnd.nextInt(maxBound - minBound + 1) + minBound;
        }
        return array;
    }

    //создание и заполнение двумерного массива случайных чисел
    static int[][] make2dArray(int qty1, int qty2, int minBound, int maxBound) {
        int[][] array = new int[qty1][qty2];
        Random rnd = new Random();
        for (int i = 0; i < qty1; i++) {
            for (int j = 0; j < qty2; j++) {
                array[i][j] = rnd.nextInt(maxBound - minBound + 1) + minBound;
            }
        }
        return array;
    }

    //вывод двумерного массива
    static void arrayOutput(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            String str = Arrays.toString(array[i]);
            System.out.println(str);
        }
    }

    //(2)сортировка массива
    static void ex2() {
        int[] array = makeArray(10, -10, 10);
        System.out.print("Unsorted: ");
        String str = Arrays.toString(array);
        System.out.println(str);
        sort(array);
        System.out.print("Sorted: ");
        str = Arrays.toString(array);
        System.out.println(str);
        System.out.println();
    }


    //бинарный поиск
    static void ex3() {
        int[] array = makeArray(13, -10, 10);
        int x = 3; //искомое число
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        sort(array);
        int minIndex = 0;
        int maxIndex = array.length - 1;
        int index = 0;
        boolean contain = false;

        do {
            index = (int) ((maxIndex + minIndex) / 2);
            if (x < array[index]) {
                maxIndex = index - 1;
            } else if (x > array[index]) {
                minIndex = index + 1;
            } else {
                contain = true;
                System.out.println("Число " + x + " содержится в массиве");
                break;
            }
        } while (maxIndex >= minIndex);
        if (!contain) {
            System.out.println("Число " + x + " не содержится в массиве");
        }
        System.out.println();
    }


    //ДЗ
    //Массивы
    //(1)
    static void ex4() {
        int[] array = new int[10];
        for (int i = 0, j = 2; i < array.length; i++, j += 2) {
            array[i] = j;
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : array) {
            System.out.println(i);
        }
        System.out.println();
    }

    //(2)
    //вывод последовательности в прямом и обратном порядке
    static void ex5() {
        int[] array = new int[99];
        for (int i = 0; i < 99; i++) {
            array[i] = i + 1;
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 98; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println();
    }

    //(3)
    //количество четных элементов
    static void ex6() {
        int[] array = makeArray(15, 0, 9);
        int qtyEven = 0;//количество четных

        for (int i = 0; i < 15; i++) {
            if (array[i] % 2 == 0) {
                qtyEven++;
            }
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Количество четных элементов: " + qtyEven);
        System.out.println();
    }

    //(4)
    //замена на 0 нечетных элементов
    static void ex7() {
        int[] array = makeArray(8, 1, 10);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i < array.length; i += 2) {
            array[i] = 0;
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }

    //(5)
    //среднее арифметическое
    static void ex8() {
        Random rnd = new Random();
        int[] arr1 = makeArray(5, 0, 5);
        int[] arr2 = makeArray(5, 0, 5);
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < arr1.length; i++) {
            sum1 += arr1[i];
            sum2 += arr2[i];
        }
        for (int i : arr1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(sum1 / arr1.length + " " + sum2 / arr2.length);
        if (sum1 / arr1.length > sum2 / arr2.length) {
            System.out.println("Среднее арифметическое для массива arr1 больше, чем для массива arr2");
        } else if (sum1 / arr1.length < sum2 / arr2.length) {
            System.out.println("Среднее арифметическое для массива arr2 больше, чем для массива arr1");
        } else {
            System.out.println("Средние арифметические для двух массивов равны");
        }
        System.out.println();
    }

    //(6)
    //является ли последовательность строго возрастающей
    static void ex9() {
        int[] array = makeArray(4, 10, 99);
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        boolean sorted = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] >= array[i + 1]) {
                System.out.println("Последовательность не является строго возрастающей");
                sorted = false;
                break;
            }
        }
        if (sorted) {
            System.out.println("Последовательность явдяется строго возрастающей");
        }
        System.out.println();
    }

    //(7)
    //фибоначчи
    static void ex10() {
        int[] array = new int[20];
        System.out.print("Первые 20 чисел Фибоначчи:");
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            if (i < 2) {
                array[i] = 1;
            } else {
                array[i] = array[i - 2] + array[i - 1];
            }
        }
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }

    //(8)
    //max элемент в массиве
    static void ex11() {
        int[] array = makeArray(12, -15, 15);
        int max = array[0];
        int maxIndex = 0;
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
                maxIndex = i;
            }
        }
        System.out.println("Максимальный элемент " + max + "; индекс его последнего вхождения " + maxIndex);
        System.out.println();
    }

    //(9)
    //массив действительных чисел
    static void ex12() {
        int[] arr1 = makeArray(10, 1, 9);
        int[] arr2 = makeArray(10, 1, 9);
        double[] arr3 = new double[10];
        int qty = 0;

        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = (double) arr1[i] / arr2[i];
            if (arr3[i] % 1 == 0) {
                qty++;
            }
        }
        String str1 = Arrays.toString(arr1);
        String str2 = Arrays.toString(arr2);
        System.out.println(str1);
        System.out.println(str2);
        DecimalFormat df = new DecimalFormat("#.##");
        for (double i : arr3) {
            System.out.print(df.format(i) + "  "); //округляем до 2х знаков после запятой
        }
        System.out.println();
        System.out.println("Количество целых элементов в массиве arr3: " + qty);
        System.out.println();
    }

    //(10)
    //какой элемент встречается чаще
    static void ex13() {
        int minBound = -1;
        int maxBound = 1;
        int[] array = makeArray(11, minBound, maxBound);
        int[][] qty = new int[2][3];
        int max1 = 0;
        int maxIndex = 0;
        boolean max2 = false; //false - нет повторов max значения

        for (int i = 0; i < qty[0].length; i++) {
            qty[0][i] = minBound + i;
        }

        String str = Arrays.toString(array);
        System.out.println(str);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < qty[0].length; j++) {
                if (array[i] == qty[0][j]) {
                    qty[1][j]++;
                }
            }
        }
        for (int i = 0; i < qty[0].length; i++) {
            if (qty[1][i] == max1) {
                max2 = true;
            } else if (qty[1][i] > max1) {
                max1 = qty[1][i];
                maxIndex = i;
                max2 = false;
            }
        }
        if (!max2) {
            System.out.println("Чаще всего встречается значение: " + qty[0][maxIndex]);
        }
        System.out.println();
    }

    //(11)
    //сумма модулей левой и правой половины
    static void ex14() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        do {
            System.out.println("Введите четное положительное число");
            if (sc.hasNextInt()) {
                number = sc.nextInt();
            }
        } while ((number < 0) || (number % 2 != 0));
        int sum1 = 0;
        int sum2 = 0;
        int[] array = makeArray(number, -5, 5);
        String str = Arrays.toString(array);
        System.out.println(str);
        for (int i = 0; i < array.length / 2; i++) {
            sum1 += Math.abs(array[i]);
            sum2 += Math.abs(array[i + (array.length / 2)]);
        }
        if (sum1 > sum2) {
            System.out.println("Сумма модулей левой половины больше");
        } else if (sum1 < sum2) {
            System.out.println("Сумма модулей правой половины больше");
        } else {
            System.out.println("Суммы модулей левой и правой половины равны");
        }
        System.out.println();
    }

    //(12)
    //
    static void ex15() {
        Random rnd = new Random();
        int[] array = new int[12];
        int qty1 = 0; //кол-во отрицательных
        int qty2 = 0; //кол-во положительных
        int result = 0;

        for (int i = 0; i < 12; i++) {
            array[i] = rnd.nextInt(21) - 10;
            if (array[i] == 0) {
                do {
                    array[i] = rnd.nextInt(21) - 10;
                } while (array[i] == 0);
            }
            if (array[i] < 0) {
                qty1++;
            } else {
                qty2++;
            }
            if (qty1 == 7) { //слишком много отрицательных
                do {
                    array[i] = rnd.nextInt(21) - 10;
                } while (array[i] < 0);
                qty1--;
            } else if (qty2 == 7) { //слишком много положительных
                do {
                    array[i] = rnd.nextInt(21) - 10;
                } while (array[i] > 0);
                qty2--;
            }

        }
        String str = Arrays.toString(array);
        System.out.println(str);
        System.out.println();
    }

    //(13)
    static void ex16() {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println("Введите натуральное число > 3");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
            }
        } while (n <= 3);
        int qty = 0;
        int[] arr1 = makeArray(n, 0, n);
        String str = Arrays.toString(arr1);
        System.out.println(str);

        int[] arr2 = new int[n];
        int k = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] % 2 == 0) {
                arr2[k] = arr1[i];
                k++;
            }
        }
        if (k != 0) {
            int[] arr3 = Arrays.copyOf(arr2, k);
            String str2 = Arrays.toString(arr3);
            System.out.println(str2);
        }
        System.out.println();
    }

    //Многомерные массивы
    //(1)
    static void ex17() {
        int[][] array = make2dArray(8, 5, 10, 99);
        arrayOutput(array);
        System.out.println();
    }

    //(2)
    static void ex18() {
        int[][] array = make2dArray(5, 8, -99, 99);
        arrayOutput(array);
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        System.out.println("Максимальный элемент массива: " + max);
        System.out.println();
    }

    //(3)
    //наибольшее по модулю произведение строки
    static void ex19() {
        int[][] array = make2dArray(7, 4, -5, 5);
        arrayOutput(array);
        int multi;
        int maxElement = 0;
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            multi = 1;
            for (int j = 0; j < array[i].length; j++) {
                multi *= Math.abs(array[i][j]);
            }
            System.out.print(multi + " ");
            if (multi > maxElement) {
                maxElement = multi;
                maxIndex = i;
            }
        }
        System.out.println();
        System.out.println("Индекс строки с наибольшим по модулю произведением элементов: " + maxIndex);
        System.out.println();
    }

    //(4)
    //max на первое место в каждой строке
    static void ex20() {
        int[][] array = make2dArray(6, 7, 0, 9);
        arrayOutput(array);
        System.out.println();
        int max;
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            max = array[i][0];
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                    maxIndex = j;
                }
            }
            if (array[i][0] < max) {
                int a = array[i][0];
                array[i][0] = max;
                array[i][maxIndex] = a;
            }
        }
        arrayOutput(array);
        System.out.println();
    }

    //(5)
    //таблица умножения
    static void ex21() {
        int[][] array = make2dArray(15, 2, 2, 9);
        arrayOutput(array);
        System.out.println();
        Random rnd = new Random();

        for (int i = 0; i < array.length; i++) {
            int[] arr1 = array[i].clone();
            Arrays.sort(arr1);
            for (int j = 0; j < array.length; j++) {
                if (j == i) {
                    continue;
                }
                int[] arr2 = array[j].clone();
                Arrays.sort(arr2);
                if (Arrays.equals(arr1, arr2)) {
                    array[j][0] = rnd.nextInt(8) + 2;
                    array[j][1] = rnd.nextInt(8) + 2;
                    i = -1;
                }
            }
        }
        arrayOutput(array);
        System.out.println();
    }

}