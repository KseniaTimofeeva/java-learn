package examination;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ksenia on 10.05.2017.
 */
public class Bank {
    private Map<Integer, Account> accounts;
    private ExecutorService pool;

    public Bank() {
        accounts = new ConcurrentHashMap<>();
        pool = Executors.newCachedThreadPool();
    }

    public void addAccount(String userName, int initialBalance) {
        Account account = new Account(userName, initialBalance);
        accounts.put(account.id, account);
    }

    public boolean transferMoney(int accId1, int accId2, int amount) {
        Future<Boolean> task = pool.submit(new Transaction(accId1, accId2, amount));
        Boolean result;
        try {
            result = task.get();
        } catch (InterruptedException | ExecutionException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    private class Transaction implements Callable {
        private int accId1;
        private int accId2;
        private int amount;

        public Transaction(int accId1, int accId2, int amount) {
            this.accId1 = accId1;
            this.accId2 = accId2;
            this.amount = amount;
        }

        @Override
        public Object call() throws Exception {
            Account acc1 = accounts.get(accId1);
            Account acc2 = accounts.get(accId2);
            System.out.println("acc" + accId1 + ": " + acc1.balance + " acc" + accId2 + ": " + acc2.balance + " amount: " + amount);

            synchronized (acc1) {
//                    System.out.println("acc1 blocked");
                synchronized (acc2) {
//                        System.out.println("acc2 blocked");
                    if (acc1 == null || acc2 == null || amount == 0) {
                        return false;
                    } else {
                        if (amount < 0) {
                            if (acc2.balance < Math.abs(amount)) {
                                return false;
                            }
                        } else if (acc1.balance < amount) {
                            return false;
                        }
                        acc1.setBalance(acc1.getBalance() - amount);
                        acc2.setBalance(acc2.getBalance() + amount);
                    }
                }
            }
            System.out.println("acc" + accId1 + ": " + acc1.balance + " acc" + accId2 + ": " + acc2.balance);
            return true;
        }
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
}
