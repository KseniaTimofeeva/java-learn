package objects.collections1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by ksenia on 10.04.2017.
 */
public class TopWords {
    public static void main(String[] args) throws IOException {
        String path = "D:\\javaProjectsTest\\dir1\\wp.txt";

        List<Map.Entry<String, Integer>> list = uniqueWords(path);
//        System.out.println(list.toString());
        topX(list, 10);
        topX(list, 100);

        groupByLength(path);

        list = uniqueCombinations(path);
        topX(list, 10);

        List<Map.Entry<Character, Double>> list2 = charactersQty(path);
        System.out.println(list2.toString());

    }

    public static List<Map.Entry<String, Integer>> uniqueWords(String path) throws IOException {
        Map<String, Integer> uniqueWordsQty = new HashMap<>();
        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            line = line.toLowerCase().replaceAll("[\\pP\u00A0=]", " ").replaceAll("\\s+", " ").trim();
            if (line.length() > 0) {
                String[] wordsLine = line.split("\\s");
                for (String word : wordsLine) {
                    if (word.length() > 2) {
                        Integer currentQty = uniqueWordsQty.get(word);
                        if (currentQty == null) {
                            currentQty = 0;
                        }
                        uniqueWordsQty.put(word, currentQty + 1);
                    }
                }
            }
        }
        return descSort(uniqueWordsQty);
    }

    public static <T1, T2> void topX(List<Map.Entry<T1, T2>> list, int qty) {
        System.out.println("Top " + qty + ":");
        int i = 0;
        for (Map.Entry<T1, T2> entry : list) {
            System.out.print(entry + ", ");
            i++;
            if (i == qty) {
                break;
            }
        }
        System.out.println("\n");
    }

    public static <T1, T2 extends Number & Comparable> List<Map.Entry<T1, T2>> descSort(Map<T1, T2> map) {
        List<Map.Entry<T1, T2>> list = new ArrayList<>();
        list.addAll(map.entrySet());

        Comparator<Map.Entry<T1, T2>> entryComparator = new Comparator<Map.Entry<T1, T2>>() {
            @Override
            public int compare(Map.Entry<T1, T2> o1, Map.Entry<T1, T2> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };

        list.sort(entryComparator);
        Collections.reverse(list);
        return list;
    }

    public static void groupByLength(String path) throws IOException {
        Map<Integer, HashSet<String>> uniqueWordsQty = new HashMap<>();
        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            line = line.toLowerCase().replaceAll("[\\pP\u00A0=]", " ").replaceAll("\\s+", " ").trim();
            if (line.length() > 0) {
                String[] wordsLine = line.split("\\s");
                for (String word : wordsLine) {
                    if (uniqueWordsQty.get(word.length()) == null) {
                        uniqueWordsQty.put(word.length(), new HashSet<>());
                    }
                    (uniqueWordsQty.get(word.length())).add(word);
                }
            }
        }
        int i = 0;
        for (Map.Entry<Integer, HashSet<String>> entry : uniqueWordsQty.entrySet()) {
            i += entry.getValue().size();
            System.out.println(entry.toString());
        }
        System.out.println();
//        System.out.println(i);
    }

    public static List<Map.Entry<String, Integer>> uniqueCombinations(String path) throws IOException {
        Map<String, Integer> uniqueCombinationQty = new HashMap<>();
        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());
        String lastWordInPrevLine = "";
        for (String line : lines) {
            line = line.toLowerCase().replaceAll("[\\pP\u00A0=]", " ").replaceAll("\\s+", " ").trim();
            if (line.length() > 0) {
                String[] wordsLine = line.split("\\s");
                List<String> combinationsInLine = new ArrayList<>();
                if (!lastWordInPrevLine.isEmpty()) {
//                    if (lastWordInPrevLine.length() > 2 && !lastWordInPrevLine.equals("the")) {
                    combinationsInLine.add(lastWordInPrevLine + " " + wordsLine[0]);
//                    }
                }
                for (int i = 1; i < wordsLine.length; i++) {
//                    if (wordsLine[i - 1].length() > 2 && !wordsLine[i - 1].equals("the") && wordsLine[i].length() > 2 && !wordsLine[i].equals("the")) {
                    combinationsInLine.add(wordsLine[i - 1] + " " + wordsLine[i]);
//                    }
                }
                for (String comb : combinationsInLine) {
                    Integer currentQty = uniqueCombinationQty.get(comb);
                    if (currentQty == null) {
                        currentQty = 0;
                    }
                    uniqueCombinationQty.put(comb, currentQty + 1);
                }
                lastWordInPrevLine = wordsLine[wordsLine.length - 1];
            }
        }
        return descSort(uniqueCombinationQty);

    }

    public static List<Map.Entry<Character, Double>> charactersQty(String path) throws IOException {
        Map<Byte, Integer> charactersQty = new HashMap<>();
        File file = new File(path);
        byte[] data = Files.readAllBytes(file.toPath());
        double allChQty = 0.0;
        for (byte ch : data) {
            if (ch >= 65 && ch <= 90) {
                ch += 32;
            }
            if (ch >= 97 && ch <= 122) {
                Integer currentQty = charactersQty.get(ch);
                if (currentQty == null) {
                    currentQty = 0;
                }
                allChQty++;
                charactersQty.put(ch, currentQty + 1);
            }
        }

        Map<Character, Double> charactersPercent = new HashMap<>();
        for (Map.Entry<Byte, Integer> entry : charactersQty.entrySet()) {
            charactersPercent.put((char) entry.getKey().byteValue(), Math.round((entry.getValue() * 100) / allChQty * 100) / 100.0);
        }
        return descSort(charactersPercent);
    }
}
