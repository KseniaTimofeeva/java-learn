package objects.ioStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by ksenia on 16.04.2017.
 */
public class MyInputStream extends InputStream {
    private Random rnd;
    private int count;
    private int totalEx;
    boolean random;
    byte saw;

    public MyInputStream(boolean random) {
        rnd = new Random(256);
        totalEx = 2048;
        this.random = random;
        if (!random) {
            saw = -1;
        }
    }

    @Override
    public int read() throws IOException {
        if (count >= totalEx) {
            count = 0;
            return -1;
        }
        count++;
        if (random) {
            return rnd.nextInt(256);
        } else {
            if (saw == 127) {
                saw = 0;
            }
            int a = ++saw;
            return a;
        }
    }

}
