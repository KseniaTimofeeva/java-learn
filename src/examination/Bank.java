package examination;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ksenia on 10.05.2017.
 */
public class Bank {
    private Map<Integer, Account> accounts;
    boolean hasNewTransaction = false;
    private Queue<Result> resultQueue;
    private Thread mailerThread;

    public Bank() {
        accounts = new ConcurrentHashMap<>();
        resultQueue = new ConcurrentLinkedQueue<>();
        mailerThread = new MailerThread();
        mailerThread.start();
    }

    public void addAccount(String userName, int initialBalance) {
        Account account = new Account(userName, initialBalance);
        accounts.put(account.id, account);
    }

    public void transferMoney(int accId1, int accId2, int amount) {
        Result resultObj = new Result(accId1, accId2, amount);
        boolean result = startTransaction(accId1, accId2, amount, resultObj);
        resultObj.setResult(result);
        resultQueue.offer(resultObj);
        synchronized (mailerThread) {
            hasNewTransaction = true;
            mailerThread.notifyAll();
        }
    }

    private boolean startTransaction(int accId1, int accId2, int amount, Result resultObj) {
        if (accId1 == accId2) {
            return false;
        }
        Account acc1 = accounts.get(accId1);
        Account acc2 = accounts.get(accId2);

        Account sync1;
        Account sync2;
        if (acc1.id < acc2.id) {
            sync1 = acc1;
            sync2 = acc2;
        } else {
            sync1 = acc2;
            sync2 = acc1;
        }
        synchronized (sync1) {
//                    System.out.println("acc1 blocked");
            synchronized (sync2) {
//                        System.out.println("acc2 blocked");
                if (acc1 == null || acc2 == null || amount == 0) {
                    return false;
                } else {
                    resultObj.setBalance1(acc1.balance);
                    resultObj.setBalance2(acc2.balance);
                    System.out.println("acc" + accId1 + ": " + acc1.balance + " acc" + accId2 + ": " + acc2.balance + " amount: " + amount);
                    if (amount < 0) {
                        if (acc2.balance < Math.abs(amount)) {
                            return false;
                        }
                    } else if (acc1.balance < amount) {
                        return false;
                    }
                    acc1.setBalance(acc1.getBalance() - amount);
                    acc2.setBalance(acc2.getBalance() + amount);
//                    System.out.println("acc" + accId1 + ": " + acc1.balance + " acc" + accId2 + ": " + acc2.balance);
                }
            }
        }
        return true;
    }


    private static class Account {
        private static int countId;
        private int id;
        private String userName;
        private int balance;

        private Account(String userName, int initialBalance) {
            id = countId++;
            this.userName = userName;
            this.balance = initialBalance;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }

    private class Result {
        private int accId1;
        private int accId2;
        private int balance1 = -1;
        private int balance2 = -1;
        private int amount;
        private boolean result;

        private Result(int accId1, int accId2, int amount) {
            this.accId1 = accId1;
            this.accId2 = accId2;
            this.amount = amount;
        }

        public void setBalance1(int balance1) {
            this.balance1 = balance1;
        }

        public void setBalance2(int balance2) {
            this.balance2 = balance2;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "Account" + accId1 + " " + balance1 + " -> Account" + accId2 + " " + balance2 + " (" + amount + "): " + result;
        }
    }


    private class MailerThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        interrupt();
                    }

                    if (hasNewTransaction) {
                        hasNewTransaction = false;
                        Result result;
                        while ((result = resultQueue.poll()) != null) {
                            System.out.println(result);
                        }
                    }
                }
            }
        }
    }
}

