package server;

import java.io.IOException;

/**
 * Created by Tuly on 11/29/2016.
 */
public class ServerApp {

    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        Server server = new Server();
        serverGUI.setStartStopServerInterface(server);
    }

}
