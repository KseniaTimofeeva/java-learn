package objects.ioStream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ksenia on 17.04.2017.
 */
public class MyEncryptInputStream extends FilterInputStream {
    private InputStream in;
    private byte key;

    protected MyEncryptInputStream(InputStream in, byte key) {
        super(in);
        this.in = in;
        this.key = key;
    }

    public int read() throws IOException {
        int b = in.read();
        if (b == -1) {
            return -1;
        }
        return b ^ key;
    }

    public void close() throws IOException {
        in.close();
    }

}
