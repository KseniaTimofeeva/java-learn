package objects.multithreading.scheduler;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by ksenia on 08.05.2017.
 */
public class TScheduler extends Thread {

    private Queue<RepeatableTask> resultTaskQueue = new PriorityBlockingQueue<>();

    private boolean newTask = false;

    public TScheduler() {
        start();
    }

    public synchronized boolean hasNewTask() {
        return newTask;
    }

    public synchronized void setNewTask(boolean newTask) {
        this.newTask = newTask;
    }

    @Override
    public void run() {
        System.out.println("Scheduler started " + System.currentTimeMillis());
        while (!isInterrupted()) {
//            System.out.println("Sch check next task /" + System.currentTimeMillis());
            RepeatableTask rt = resultTaskQueue.peek();
            try {
                synchronized (this) {
                    if (rt == null) {
//                        System.out.println("Sch is waiting");
                        wait();
                    } else {
                        long waitTime = rt.nextStartTime - System.currentTimeMillis();
                        if (waitTime > 0) {
                            System.out.println("Sch is waiting " + waitTime);
                            wait(waitTime);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt();
            }
//            System.out.println("Sch doesn't sleep /" + System.currentTimeMillis());
            if (hasNewTask() || rt == null) {
                setNewTask(false);
                continue;
            }
            if (rt.nextStartTime <= System.currentTimeMillis()) {
                rt = resultTaskQueue.poll();
//                System.out.println("start " + rt);
                startTask(rt);
                resultTaskQueue.offer(rt);
//                System.out.println("Sch adds repeat " + rt + " /" + System.currentTimeMillis());
            }
        }
    }


    public void addTask(Task task, int repeatTime) {
        RepeatableTask rt = new RepeatableTask(task, repeatTime);
//        System.out.println("Sch adds new " + rt + " /" + System.currentTimeMillis());
        resultTaskQueue.offer(rt);
        setNewTask(true);
        synchronized (this) {
            notifyAll();
        }
    }

    private void startTask(RepeatableTask rt) {
//        System.out.println("Sch starts " + rt + " /" + System.currentTimeMillis());
        Thread thread = new Thread(rt.task);
        thread.start();
        rt.setNextStartTime();
    }


    private class RepeatableTask implements Comparable {
        private Task task;
        private int repeatTime;
        private long nextStartTime;

        private RepeatableTask(Task task, int repeatTime) {
            this.task = task;
            this.repeatTime = repeatTime;
            nextStartTime = System.currentTimeMillis();
        }

        private void setNextStartTime() {
            nextStartTime = System.currentTimeMillis() + repeatTime;
        }

        @Override
        public int compareTo(Object o) {
            RepeatableTask task1 = (RepeatableTask) o;
            if (nextStartTime < task1.nextStartTime) {
                return -1;
            } else if (nextStartTime > task1.nextStartTime) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "{Task " + task.getName() + ": repeat " + repeatTime + ", next " + nextStartTime + "}";
        }
    }
}
