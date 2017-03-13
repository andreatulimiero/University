package client;

import java.io.IOException;

/**
 * Created by Tuly on 11/29/2016.
 */
public class ClientApp {

    public static void main(String[] args){

        ClientGUI clientGUI = new ClientGUI();
        try {
            Client client = new Client();
            clientGUI.setSendMessageInterface(client);
            client.setAuthenticateInterface(clientGUI);
        } catch (IOException e) {
            System.out.println("Impossible to connect to server");
        }
    }

}
