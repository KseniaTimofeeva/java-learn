package objects.ioStream;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ksenia on 17.04.2017.
 */
public class MyCryptOutputStream extends FilterOutputStream {
    private OutputStream out;
    private byte key;
    private byte[] password;
    private int cursor;

    public MyCryptOutputStream(OutputStream out, byte key) {
        super(out);
        this.out = out;
        this.key = key;
    }

    public MyCryptOutputStream(OutputStream out, byte[] password) {
        super(out);
        this.out = out;
        this.password = password;
        cursor = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if (password == null) {
            out.write(b ^ key);
        } else {
            int c = cursor;
            cursor++;
            if (cursor == password.length) {
                cursor = 0;
            }
            out.write(b ^ password[c]);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (password == null) {
            for (int i = off; i < len; i++) {
                b[i] ^= key;
            }
        } else {
            for (int i = off; i < len; i++) {
                b[i] ^= password[cursor];
                cursor++;
                if (cursor == password.length) {
                    cursor = 0;
                }
            }
        }
        out.write(b, off, len);
    }
}
