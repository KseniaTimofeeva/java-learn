package objects.multithreading;

import objects.collections1.TopWords;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static objects.collections1.TopWords.topX;

/**
 * Created by ksenia on 28.04.2017.
 */
public class TopWordsMultiT {

    private Map<String, Integer> uniqueWordsQty;

    private BlockingQueue<String> lineQueue = new LinkedBlockingQueue<>();
    private Queue<Map<String, Integer>> resultQueue = new ConcurrentLinkedQueue<>();

    private static final String STOP = new String();

    public TopWordsMultiT() {
        uniqueWordsQty = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        TopWordsMultiT topWordsMultiT = new TopWordsMultiT();
        String path = "D:\\javaProjectsTest\\dir1\\wp.txt";

        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());

        int processors = Runtime.getRuntime().availableProcessors();

        for (String line : lines) {
            topWordsMultiT.lineQueue.offer(line);
        }
        topWordsMultiT.lineQueue.offer(STOP);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < processors; i++) {
            Thread thr = new Thread(topWordsMultiT.new UniqueWordsCounter(/*subList*/));
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
        topWordsMultiT.integrateAndPrintTopWords();
    }

    private void integrateAndPrintTopWords() {
        Map<String, Integer> map;
        while ((map = resultQueue.poll()) != null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer currentQty = uniqueWordsQty.get(key);
                if (currentQty == null) {
                    currentQty = 0;
                }
                uniqueWordsQty.put(key, currentQty + entry.getValue());
            }
        }

        List<Map.Entry<String, Integer>> list = TopWords.descSort(uniqueWordsQty);
        topX(list, 100);
    }


    private class UniqueWordsCounter extends Thread {
        private Map<String, Integer> subUniqueWordsQty;

        public UniqueWordsCounter() {
            subUniqueWordsQty = new HashMap<>();
        }

        @Override
        public void run() {


            String taskLine = null;
            while (true) {
                try {
                    taskLine = lineQueue.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }

                if (taskLine == STOP) {
                    lineQueue.offer(taskLine);
                    break;
                }

                taskLine = taskLine.toLowerCase().replaceAll("[\\pP\u00A0=]", " ").replaceAll("\\s+", " ").trim();
                if (taskLine.length() > 0) {
                    String[] wordsLine = taskLine.split("\\s");

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
            resultQueue.offer(subUniqueWordsQty);

        }
    }
}
