package objects.multithreading.scheduler;

import java.util.concurrent.TimeUnit;

/**
 * Created by ksenia on 08.05.2017.
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println("Task " + name + " is running /" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
