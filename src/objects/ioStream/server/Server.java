package objects.ioStream.server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by ksenia on 19.04.2017.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ssocket = new ServerSocket(12345)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();
                try (InputStreamReader in = new InputStreamReader(sock.getInputStream());
                     BufferedReader bufr = new BufferedReader(in);
                     OutputStream outputStream = sock.getOutputStream()) {

                    char[] buf = new char[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = bufr.read(buf)) != -1) {
                        sb.append(buf, 0, len);
                    }
                    System.out.println(sb.toString());
                }
            }
        }
    }
}
