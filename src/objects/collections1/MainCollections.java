package objects.collections1;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by ksenia on 07.04.2017.
 */
public class MainCollections {
    public static void main(String[] args) throws IOException {
//        shuffle
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(Arrays.toString(list.toArray()));
        shuffle(list);
        System.out.println(Arrays.toString(list.toArray()) + "\n");


//        diff & intersect
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            set1.add(i + "");
            set2.add(i + 5 + "");
        }
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println(Arrays.toString(set2.toArray()));

        System.out.println("Diff: " + Arrays.toString(difference(set1, set2).toArray()));
        System.out.println("Intersect: " + Arrays.toString(intersect(set1, set2).toArray()) + "\n");

//        unique words
//        String path = "D:\\javaProjectsTest\\dir1\\Garri Potter2.txt";
        String path = "D:\\javaProjectsTest\\dir1\\lorem.txt";

        Set<String> uniqueWords = uniqueWords(path);
        System.out.println("Unique words qty = " + uniqueWords.size() + "\n");
        int i = 0;
        for (String s : uniqueWords) {
            if (i < 10) {
                System.out.print(s + ", ");
            }
            if (i == 9) {
                System.out.println("");
                i = -1;
            }
            i++;
        }
        System.out.println();

    }

    static <T> void shuffle(List<T> list) {
        Random rnd = new Random();
        int index;

        for (int i = 0; i < list.size(); i++) {
            index = rnd.nextInt(list.size() - i);
            T t = list.get(index);
            list.set(index, list.get(list.size() - i - 1));
            list.set(list.size() - i - 1, t);
        }
    }

    static <T> Set<T> difference(Set<T> set1, Set<T> set2) {
        Set<T> newSet = new HashSet<>();
        newSet.addAll(set1);
        newSet.removeAll(set2);
        return newSet;
    }

    static <T> Set<T> intersect(Set<T> set1, Set<T> set2) {
        Set<T> newSet = new HashSet<>();
        newSet.addAll(set1);
        newSet.retainAll(set2);
        return newSet;
    }

    static Set<String> uniqueWords(String path) throws IOException {
        Set<String> uniqueWords = new HashSet<>();
        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());
        int i = 0;
        for (String line : lines) {
            line = line.toLowerCase().replaceAll("[\\pP\u00A0]", " ").replaceAll("\\s+", " ").trim();
            if (line.length() > 0) {
                String[] wordsLine = line.split("\\s");
                uniqueWords.addAll(Arrays.asList(wordsLine));
                i++;
            }
        }
        return uniqueWords;
    }
}
