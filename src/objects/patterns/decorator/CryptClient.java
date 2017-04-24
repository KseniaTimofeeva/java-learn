package objects.patterns.decorator;

import objects.ioStream.MyCryptInputStream;
import objects.ioStream.MyCryptOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created by ksenia on 21.04.2017.
 */
public class CryptClient {
    private SocketAddress serverAddr;
    private Scanner scanner;
    private byte[] password;

    public CryptClient(SocketAddress serverAddr, Scanner scanner, byte[] password) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
        this.password = password;
    }

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    private void start() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("Enter message to send: ");
            String msg = scanner.nextLine();
            sendMessageAndGetAnswer(msg);
        }
    }

    private void sendMessageAndGetAnswer(String msg) throws IOException, ClassNotFoundException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (ObjectOutputStream objOut = new ObjectOutputStream(new MyCryptOutputStream(sock.getOutputStream(), password));
                 ObjectInputStream objIn = new ObjectInputStream(new MyCryptInputStream(sock.getInputStream(), password))) {

                objOut.writeObject(msg);
                objOut.flush();

                System.out.println((String) objIn.readObject());
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        byte[] password = "password".getBytes();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter server address");
        String addr = scanner.nextLine();

        CryptClient client = new CryptClient(parseAddress(addr), scanner, password);
        client.start();
    }
}
