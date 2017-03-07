package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Tuly on 11/29/2016.
 */
public class Server implements StartStopServerInterface{

    private static final int PORT = 3000;

    private ServerSocket serverSocket;
    private List<Socket> clients;
    private Map<String, File> users;

    public Server() {
        users = new HashMap<>();
    }

    @Override
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clients = new ArrayList<>();
        new Thread(new ConnectionListener()).start();
        System.out.println("Server up and running on port: " + PORT);
    }

    @Override
    public void stopServer() {
        try {
            for (Socket socket : clients) {
                socket.close();
            }
            serverSocket.close();
            users.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void addUser(String username){
        // Check if exist, if it does, open in append mode
        File file = new File(username.concat(".txt"));
        users.put(username, file);
        System.out.println("User authenticated -> " + users);
    }

    private class ConnectionListener implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        clients.add(clientSocket);
                        new Thread(new ClientHandler(clientSocket)).start();
                        System.out.println("Client connected");
                    }
                } catch (IOException e) {
                    System.out.println("Server closed");
                    return;
                }
            }
        }
    }

    private class ClientHandler implements Runnable {

        private Socket socket;
        private String username;

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
                    StringTokenizer tokenizer = new StringTokenizer(lineIn, ":");
                    if(tokenizer.nextToken().equalsIgnoreCase("Name")) {
                        username = tokenizer.nextToken();
                        addUser(username);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.println("authenticated");
                        out.flush();
                    } else
                        new Thread(new MessageSaver(username, lineIn)).start();
                }
            } catch (NoSuchElementException e){
                System.out.println("Client disconnected");
            }
            catch (IOException e) {
                System.out.println("Impossible to get input stream");
            }
        }
    }

    private class MessageSaver implements Runnable{

        private String username, message;

        public MessageSaver(String username, String message) {
            this.username = username;
            this.message = message;
        }

        @Override
        public void run() {
            File file = users.get(username);
            try {
                FileWriter outFile = new FileWriter(file, file.exists());
                outFile.write(message.concat("\n"));
                outFile.flush();
                outFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
