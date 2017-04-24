package objects.ioStream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ksenia on 17.04.2017.
 */
public class MyCryptInputStream extends InputStream {
    private InputStream in;
    private byte key;
    private byte[] password;
    private int cursor;

    public MyCryptInputStream(InputStream in, byte key) {
//        super(in);
        this.in = in;
        this.key = key;
    }

    public MyCryptInputStream(InputStream in, byte[] password) {
//        super(in);
        this.in = in;
        this.password = password;
        cursor = 0;
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        if (b == -1) {
            return -1;
        }
        if (password == null) {
            return b ^ key;
        } else {
            int c = cursor;
            cursor++;
            if (cursor == password.length) {
                cursor = 0;
            }
            return b ^ password[c];
        }
    }

    @Override
    public int read(byte[] b) throws IOException {

        int len = in.read(b);
        if (len == -1) {
            return -1;
        } else {
            if (password == null) {
                for (int i = 0; i < len; i++) {
                    b[i] ^= key;
                }
            } else {
                for (int i = 0; i < len; i++) {
                    b[i] ^= password[cursor];
                    cursor++;
                    if (cursor == password.length) {
                        cursor = 0;
                    }
                }
            }
            return len;
        }
    }
}
