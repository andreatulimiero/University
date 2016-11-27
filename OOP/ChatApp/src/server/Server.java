package server;

import jdk.nashorn.internal.ir.Block;
import jdk.nashorn.internal.runtime.arrays.IteratorAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static server.ServerStatus.RUNNING;
import static server.ServerStatus.STARTING;
import static server.ServerStatus.STOPPED;

/**
 * Created by Utente on 11/26/2016.
 */
public class Server implements ServerStartStopInterface {

    private static final int PORT = 3000;
    private static ServerStatus serverStatus;
    private ServerSocket serverSocket;
    private List<Socket> clientsList;
    private Map<Socket, BlockingDeque<String>> clientsMessages;


    public Server() {
        clientsList = new ArrayList<>();
        clientsMessages = new HashMap<>();
    }

    @Override
    public void startServer() throws IOException {
        if (serverStatus == RUNNING) {
            System.out.println("Server is already running!");
            return;
        }
        serverStatus = STARTING;
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server ready and listening on port: " + PORT);
        serverStatus = RUNNING;

        new Thread(() -> {
            try {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    addClient(clientSocket);
                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                System.out.println("Server socket has been closed");
            }
        }).start();
    }

    @Override
    public void stopServer() throws IOException {
        if (serverStatus == STOPPED) {
            System.out.println("Server is already stopped!");
            return;
        }
        System.out.println("Closing server ...");
        clientsList.clear();
        if (!serverSocket.isClosed()) serverSocket.close();
        serverStatus = STOPPED;
    }

    private synchronized void addClient(Socket socket) {
        clientsList.add(socket);
        clientsMessages.put(socket, new LinkedBlockingDeque<>());
    }

    private synchronized void removeClient(Socket socket) {
        clientsList.remove(socket);
    }

    private class BroadcastSender implements Runnable {

        private String message;

        public BroadcastSender(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.print("Adding '" + message + "' to clients messages -> ");
            for (Map.Entry<Socket, BlockingDeque<String>> clientMessages : clientsMessages.entrySet()) {
                clientMessages.getValue().add(message);
                System.out.println(clientMessages.getValue());
            }
//            System.out.println("Broadcasting: " + message);
//            PrintWriter out;
//            for (Socket socket : clientsList) {
//                System.out.println("Sending to " + socket.getInetAddress());
//                try {
//                    out = new PrintWriter(socket.getOutputStream());
//                    out.println(message);
//                    out.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

        }
    }

    private class PersonalSender implements Runnable {

        private Socket socket;

        public PersonalSender(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BlockingDeque<String> messages = clientsMessages.get(socket);
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                Iterator iterator = messages.iterator();
                while (iterator.hasNext()){
                    out.println(iterator.next());
                    out.flush();
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable {

        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Received client connection");
            Scanner in = null;
            try {
                in = new Scanner(socket.getInputStream());

                while (true) {
                    String lineIn = in.nextLine();
                    if (lineIn.equalsIgnoreCase("quit")) break;
                    if (lineIn.equalsIgnoreCase("__get_messages__"))
                        new Thread(new PersonalSender(socket)).start();
                    else
                        new Thread(new BroadcastSender(lineIn)).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Closing client socket");
                if (in != null) in.close();
                removeClient(socket);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
