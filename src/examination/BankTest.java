package examination;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ksenia on 10.05.2017.
 */
public class BankTest {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            bank.addAccount("User " + i, rnd.nextInt(10000));
        }

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    bank.transferMoney(rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10000));
                }
            };
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }


    }
}
