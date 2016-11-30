package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Tuly on 11/29/2016.
 */
public class Server {

    private ServerSocket serverSocket;
    private static final int PORT = 3000;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void startServer() {
        new Thread(new ConnectionListener()).start();
        System.out.println("Server up and running on port: " + PORT);
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ConnectionListener implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        new Thread(new ClientHandler(serverSocket.accept())).start();
                        System.out.println("Client connected");
                    }
                } catch (IOException e) {
//                    e.printStackTrace();
                    System.out.println("Server closed");
                }
            }
        }
    }

    private class ClientHandler implements Runnable {
        Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Scanner in = new Scanner(socket.getInputStream());
                while (true) {
                    String lineIn = in.nextLine();
                    System.out.println("Received: " + lineIn);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client disconnected");
            }
        }
    }
}
