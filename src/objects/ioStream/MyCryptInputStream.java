package objects.ioStream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ksenia on 17.04.2017.
 */
public class MyCryptInputStream extends FilterInputStream {
    private InputStream in;
    private byte key;
    private byte[] password;
    private int cursor;

    public MyCryptInputStream(InputStream in, byte key) {
        super(in);
        this.in = in;
        this.key = key;
    }

    public MyCryptInputStream(InputStream in, byte[] password) {
        super(in);
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
            return (b ^ password[c]) & 0xFF;
        }
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {

        int leng = in.read(b, off, len);
        if (leng == -1) {
            return -1;
        } else {
            if (password == null) {
                for (int i = off; i < off + leng; i++) {
                    b[i] ^= key;
                    b[i] &= 0xFF;
                }
            } else {
                for (int i = off; i < off + leng; i++) {
                    b[i] ^= password[cursor];
                    b[i] &= 0xFF;
                    cursor++;
                    if (cursor == password.length) {
                        cursor = 0;
                    }
                }
            }
            return leng;
        }
    }
}
