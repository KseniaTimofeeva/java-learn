package objects.multithreading.scheduler;

/**
 * Created by ksenia on 08.05.2017.
 */
public class TSchedulerTest {
    public static void main(String[] args) {
        TScheduler tScheduler = new TScheduler();
        tScheduler.addTask(new Task("1"), 5000);
        tScheduler.addTask(new Task("2"), 7000);
        tScheduler.addTask(new Task("3"), 10000);
    }
}
