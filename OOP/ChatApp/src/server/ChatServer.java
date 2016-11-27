package server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Utente on 11/26/2016.
 */
public class ChatServer {

    public static void main(String[] args) {

        Server server = new Server();
        ServerGUI serverGUI = new ServerGUI(server);
    }

}
