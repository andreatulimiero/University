package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
    }

    private void authenticate(){
        out.println("Name: Alamanas");
        out.flush();
    }

}
