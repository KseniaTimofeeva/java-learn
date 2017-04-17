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
    private String password;

    public MyCryptOutputStream(OutputStream out, byte key) {
        super(out);
        this.out = out;
        this.key = key;
    }

    public MyCryptOutputStream(OutputStream out, String password) {
        super(out);
        this.out = out;
        this.password = password;
    }

    public void write(int b) throws IOException{
        out.write(b ^ key);
    }

    public void close() throws IOException {
        out.close();
    }
}
