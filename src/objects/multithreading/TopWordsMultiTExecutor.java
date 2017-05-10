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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

import static objects.collections1.TopWords.topX;

/**
 * Created by ksenia on 05.05.2017.
 */
public class TopWordsMultiTExecutor {
    private Map<String, Integer> uniqueWordsQty;
    private List<Future<Map<String, Integer>>> tasks;

    public TopWordsMultiTExecutor() {
        uniqueWordsQty = new HashMap<>();
        tasks = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        TopWordsMultiTExecutor topWordsMultiTExecutor = new TopWordsMultiTExecutor();
        String path = "D:\\javaProjectsTest\\dir1\\wp.txt";

        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());

        int processors = Runtime.getRuntime().availableProcessors();
        int qtyLinesToThr = lines.size() / processors;

        ExecutorService pool = Executors.newFixedThreadPool(processors);

        for (int i = 0; i < processors; i++) {
            List<String> subList;
            if (i != (processors - 1)) {
                subList = lines.subList(qtyLinesToThr * i, qtyLinesToThr * (i + 1));
            } else {
                subList = lines.subList(qtyLinesToThr * i, lines.size());
            }
            Future<Map<String, Integer>> task = pool.submit(new UniqueWordsCounter(subList));
            topWordsMultiTExecutor.tasks.add(task);
        }

        for (Future<Map<String, Integer>> task : topWordsMultiTExecutor.tasks) {
            try {
                Map<String, Integer> result = task.get();
                for (Map.Entry<String, Integer> entry : result.entrySet()) {
                    String key = entry.getKey();
                    Integer currentQty = topWordsMultiTExecutor.uniqueWordsQty.get(key);
                    if (currentQty == null) {
                        currentQty = 0;
                    }
                    topWordsMultiTExecutor.uniqueWordsQty.put(key, currentQty + entry.getValue());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                pool.shutdown();
            }
        }

        List<Map.Entry<String, Integer>> list = TopWords.descSort(topWordsMultiTExecutor.uniqueWordsQty);

        topX(list, 100);
    }


    private static class UniqueWordsCounter implements Callable {
        private Map<String, Integer> subUniqueWordsQty;
        private List<String> subList;

        public UniqueWordsCounter(List<String> subList) {
            subUniqueWordsQty = new HashMap<>();
            this.subList = subList;
        }


        @Override
        public Object call() throws Exception {
            for (String line : subList) {
                line = line.toLowerCase().replaceAll("[\\pP\u00A0=]", " ").replaceAll("\\s+", " ").trim();
                if (line.length() > 0) {
                    String[] wordsLine = line.split("\\s");
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
            return subUniqueWordsQty;
        }

    }
}
