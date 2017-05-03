package objects.multithreading;

import objects.collections1.TopWords;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static objects.collections1.TopWords.topX;

/**
 * Created by ksenia on 28.04.2017.
 */
public class TopWordsMultiT {

    private Map<String, Integer> uniqueWordsQty;

    public TopWordsMultiT() {
        uniqueWordsQty = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        TopWordsMultiT topWordsMultiT = new TopWordsMultiT();
        String path = "D:\\javaProjectsTest\\dir1\\wp.txt";

        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());

        int processors = Runtime.getRuntime().availableProcessors();
        int qtyLinesToThr = lines.size() / processors;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < processors; i++) {
            List<String> subList;
            if (i != (processors - 1)) {
                subList = lines.subList(qtyLinesToThr * i, qtyLinesToThr * (i + 1));

            } else {
                subList = lines.subList(qtyLinesToThr * i, lines.size());
            }
            Thread thr = new Thread(topWordsMultiT.new UniqueWordsCounter(subList));
            threads.add(thr);
            thr.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
                e.printStackTrace();
            }
        }


        topWordsMultiT.printTopWords();
    }

    private void printTopWords() {
        List<Map.Entry<String, Integer>> list = TopWords.descSort(uniqueWordsQty);
        topX(list, 100);
    }

    private class UniqueWordsCounter implements Runnable {

        private List<String> subList;
        private Map<String, Integer> subUniqueWordsQty;

        public UniqueWordsCounter(List<String> subList) {
            this.subList = subList;
            subUniqueWordsQty = new HashMap<>();
        }

        @Override
        public void run() {

//            int wordsqty = 0;

            for (String line : subList) {
                line = line.toLowerCase().replaceAll("[\\pP\u00A0=]", " ").replaceAll("\\s+", " ").trim();
                if (line.length() > 0) {
                    String[] wordsLine = line.split("\\s");

//                    wordsqty += wordsLine.length;

                    for (String word : wordsLine) {
                        if (word.length() > 2) {
                            Integer currentQty = subUniqueWordsQty.get(word);
                            if (currentQty == null) {
                                currentQty = 0;
                            }
                            subUniqueWordsQty.put(word, currentQty + 1);
                        }
                    }
                }
            }

//            System.out.println(Thread.currentThread().getName() + " : " + wordsqty);

            addToTop(subUniqueWordsQty);
        }


        private synchronized void addToTop(Map<String, Integer> subUniqueWordsQty) {
            for (Map.Entry<String, Integer> entry : subUniqueWordsQty.entrySet()) {
                String key = entry.getKey();
                Integer currentQty = uniqueWordsQty.get(key);
                if (currentQty == null) {
                    currentQty = 0;
                }
                uniqueWordsQty.put(key, currentQty + entry.getValue());
            }
        }
    }
}
