package objects.multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksenia on 26.04.2017.
 */
public class MultiThrExamples {
    public static void main(String[] args) throws InterruptedException {

        List<Thread> list = new ArrayList<>();

        list.add(new Thread(new InterThread1()));
        list.add(new Thread(new InterThread2()));
        list.add(new Thread(new InterThread3()));

        for (Thread thread : list) {
            thread.start();
        }

        Thread.sleep(3000);

        for (Thread thread : list) {
            thread.interrupt();
        }
    }

    public static class InterThread1 implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static class InterThread2 implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Hello2");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static class InterThread3 implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Hello3");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
