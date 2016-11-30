package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Tuly on 11/29/2016.
 */
public class Client {

    private final static String HOST = "localhost";
    private final static int PORT = 3000;

    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public Client() throws IOException {
        socket = new Socket(HOST, PORT);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        new Thread(new Authenticate()).start();
    }

    private class MessageListener implements Runnable {
        @Override
        public void run() {
            while (true) {
                String inLine = in.nextLine();
                System.out.println(inLine);
            }
        }
    }

    private class Authenticate implements Runnable {

        @Override
        public void run() {
            do {
                out.println("Name: Alamanas");
                out.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("Sending authentication");
                    if (in.nextLine().equalsIgnoreCase("authenticated")) {
                        out.println("Message to be saved");
                        out.flush();
                        return;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Connection with server was interrupted");
                }
            } while (true);
        }
    }

}
