package server;

import java.io.IOException;

/**
 * Created by Tuly on 11/29/2016.
 */
public class ServerApp {

    public static void main(String[] args){
        Server server = null;
        try {
            server = new Server();
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
