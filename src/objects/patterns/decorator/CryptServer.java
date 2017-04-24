package objects.patterns.decorator;

import com.itmo.iostreams.serial.print.Message;
import objects.ioStream.MyCryptInputStream;
import objects.ioStream.MyCryptOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ksenia on 21.04.2017.
 */
public class CryptServer {
    private int port;
    private byte[] password;

    public CryptServer(int port, byte[] password) {
        this.port = port;
        this.password = password;
    }

    private void start() throws IOException, ClassNotFoundException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();
                try (ObjectInputStream objIn = new ObjectInputStream(new MyCryptInputStream(sock.getInputStream(), password));
                     ObjectOutputStream objOut = new ObjectOutputStream(new MyCryptOutputStream(sock.getOutputStream(), password))) {

                    Object obj = objIn.readObject();
                    System.out.println(obj);

                    objOut.writeObject("Delivered");
                    objOut.flush();
                } finally {
                    sock.close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        int port = 12345;
        byte[] password = "password".getBytes();

        CryptServer cryptServer = new CryptServer(port, password);

        cryptServer.start();
    }
}
