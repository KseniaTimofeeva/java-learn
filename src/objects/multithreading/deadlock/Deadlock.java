package objects.multithreading.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ksenia on 10.05.2017.
 */
public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
        final Object o1 = new Object();
        final Object o2 = new Object();

        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                    synchronized (o1) {
                        latch.countDown();
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (o2) {
                            System.out.println("Thread1 locked objects");
                        }
                    }

            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                    synchronized (o2) {
                        latch.countDown();
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (o1) {

                            System.out.println("Thread2 locked objects");
                        }
                    }

            }
        };

        thread1.start();
        thread2.start();
    }
}
