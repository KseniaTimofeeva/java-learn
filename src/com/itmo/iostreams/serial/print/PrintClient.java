package com.itmo.iostreams.serial.print;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by xmitya on 28.08.16.
 */
public class PrintClient {

    private SocketAddress serverAddr;

    private String name;

    private Scanner scanner;

    public PrintClient(SocketAddress serverAddr, Scanner scanner) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
    }

    private void start() throws IOException, ClassNotFoundException {
        System.out.println("Enter your name: ");

        name = scanner.nextLine();

        while (true) {
            System.out.println("Enter message to send: ");

            String msg = scanner.nextLine();

            if ("/exit".equals(msg))
                break;
            else if ("/nick".equals(msg)) {
                System.out.println("Enter new name:");

                name = scanner.nextLine();
                continue;
            } else if ("/myaddr".equals(msg)) {
                printAddresses();
                continue;

            } else if ("/list_users".equals(msg)) {
                listUsers();
                continue;

            } else if ("/server_time".equals(msg)) {
                serverTime();
                continue;

            } else if ("/ping".equals(msg)) {
                sendPing();
                continue;
            }


            buildAndSendMessage(msg);
        }
    }

    private void printAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                System.out.println(i.getHostAddress());
            }
        }
    }

    private void buildAndSendMessage(String msg) throws IOException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message);

        System.out.println("Sent: " + message);
    }

    private void sendPrintMessage(Message msg) throws IOException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (OutputStream out = sock.getOutputStream();
                 ObjectOutputStream objOut = new ObjectOutputStream(out);
                 ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())) {

                objOut.writeObject(msg);

                objOut.flush();
            }
        }
    }

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    private void sendPing() throws IOException, ClassNotFoundException {
        long roundTripTime = (long) sendCommandGetAnswer(new Ping());
        System.out.println("Round trip time: " + new DecimalFormat("#0.00").format(roundTripTime / 1000000.0) + " ms");
    }

    private void serverTime() throws IOException, ClassNotFoundException {
        Object object = sendCommandGetAnswer(new ServerTime());
        if (object instanceof Date) {
            Date date = (Date) object;
            System.out.println("Server time: " + date);
        }
    }

    private void listUsers() throws IOException, ClassNotFoundException {
        Object object = sendCommandGetAnswer(new ListUsers());
        if (object instanceof HashSet) {
            HashSet allUsers = (HashSet) object;
            System.out.println("Users: ");
            System.out.println(allUsers.toString());
        }
    }

    private <T extends Command> Object sendCommandGetAnswer(T command) throws IOException, ClassNotFoundException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream());
                 ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())) {

                long start = System.nanoTime();
                objOut.writeObject(command);
                objOut.flush();

                Object object = objIn.readObject();
                long end = System.nanoTime();

                if (object instanceof Command) {
                    if (((Command) object).getCode() == 1) {
                        return end - start;
                    } else {
                        return ((Command) object).getAnswer();
                    }
                } else {
                    return null;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        PrintClient client = new PrintClient(parseAddress(addr), scanner);

        client.start();
    }
}
