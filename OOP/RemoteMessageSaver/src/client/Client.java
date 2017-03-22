package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Tuly on 11/29/2016.
 */
public class Client implements SendMessageInterface{

    private final static String HOST = "localhost";
    private final static int PORT = 3000;

    private AuthenticateInterface authenticateInterface;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public Client() throws IOException {
        socket = new Socket(HOST, PORT);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        new Thread(new Authenticate()).start();
    }

    public void setAuthenticateInterface(AuthenticateInterface authenticateInterface) {
        this.authenticateInterface = authenticateInterface;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending: " + message);
        new Thread(new MessageSender(message)).start();
    }

    private class MessageSender implements Runnable{

        private String message;

        public MessageSender(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            out.println(message);
            out.flush();
        }
    }

    private class MessageListener implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    String inLine = in.nextLine();
                    System.out.println(inLine);
                } catch (NoSuchElementException e){
                    System.out.println("There was an error talking with the server");
                    return;
                }
            }
        }
    }

    private class Authenticate implements Runnable {

        @Override
        public void run() {
            do {
                out.println("Name: Alamanas".concat(String.valueOf((int)(Math.random()*10))));
                out.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("Sending authentication");
                    if (in.nextLine().equalsIgnoreCase("authenticated")) {
                        System.out.println("Authenticated");
                        new Thread(new MessageListener()).start();
                        break;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Connection with server was interrupted");
                }
            } while (true);
        }
    }

}
