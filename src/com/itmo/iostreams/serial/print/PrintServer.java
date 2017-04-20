package com.itmo.iostreams.serial.print;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xmitya on 28.08.16.
 */
public class PrintServer {

    private int port;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    private Set<String> allUsers;

    private File allUsersFile;

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException, ClassNotFoundException {
        allUsersFile = new File("C:\\projects\\java-learn\\serverUsers.bin");
        if (!allUsersFile.exists()) {
            allUsersFile.createNewFile();
        }
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            if (allUsersFile.length() != 0) {
                try (ObjectInputStream objFIn = new ObjectInputStream(new FileInputStream(allUsersFile))){
                    allUsers = (HashSet) objFIn.readObject();
                }
            }
            if (allUsers == null) {
                allUsers = new HashSet<>();
            }

            while (true) {
                Socket sock = ssocket.accept();

                try {
                    process(sock);
                } catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");

                    e.printStackTrace();
                } finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();

        boolean hasAdded = allUsers.add(host);

        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
             OutputStream out = sock.getOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(out)) {
            Object obj = objIn.readObject();

            if (obj instanceof Command) {
                switch (((Command) obj).getCode()) {
                    case 1:
                        Ping ping = ((Command) obj).getCommand();
                        sendPingBack(objOut, ping);
                        break;
                    case 2:
                        ListUsers listUsers = ((Command) obj).getCommand();
                        listUsers(objOut, listUsers);
                        break;
                    case 3:
                        ServerTime serverTime = ((Command) obj).getCommand();
                        sendServerTime(objOut, serverTime);
                        break;
                }
            } else {
                printMessage((Message) obj, host);
            }
            if (hasAdded) {
                try (FileOutputStream fOut = new FileOutputStream(allUsersFile);
                     ObjectOutputStream objFOut = new ObjectOutputStream(fOut)) {
                    objFOut.writeObject(allUsers);
                }
            }
        } catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    private void sendPingBack(ObjectOutputStream objOut, Ping ping) throws IOException {
        ping.setAnswer(null);
        sendResponse(objOut, ping);
    }

    private void sendServerTime(ObjectOutputStream objOut, ServerTime serverTime) throws IOException {
        serverTime.setAnswer(new Date());
        sendResponse(objOut, serverTime);
    }

    private void listUsers(ObjectOutputStream objOut, ListUsers listUsers) throws IOException {
        listUsers.setAnswer(allUsers);
        sendResponse(objOut, listUsers);
    }

    private <T extends Command> void sendResponse(ObjectOutputStream objOut, T response) throws IOException {
        objOut.writeObject(response);
        objOut.flush();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
