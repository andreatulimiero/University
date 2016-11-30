package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Utente on 11/26/2016.
 */
public class Client implements SendMessageInterface {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3000;

    private ReceiveMessageInterface receiveMessageInterface;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public Client() throws IOException {
        socket = new Socket(HOST, PORT);
        System.out.println("Connected to host: " + HOST + " on port: " + PORT);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        new Thread(new MessageRetriever()).start();
    }

    public void setReceiveMessageInterface(ReceiveMessageInterface receiveMessageInterface) {
        this.receiveMessageInterface = receiveMessageInterface;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending: " + message);
        out.println(message.trim());
        out.flush();
    }

    private class MessageRetriever implements Runnable {

        @Override
        public void run() {
            new Thread(new MessageAsker()).start();
                while (true) {
                    String message = in.nextLine();
                    System.out.println("Received: " + message);
                    receiveMessageInterface.receivedMessage(message);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }

        private class MessageAsker implements Runnable {

            @Override
            public void run() {
                while (true) {
                    out.println("__get_messages__");
                    out.flush();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
