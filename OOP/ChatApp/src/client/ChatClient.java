package client;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Utente on 11/26/2016.
 */
public class ChatClient {

    private static ChatGUI chatGUI;
    private static final String RETRY_CONNECTION_MESSAGE = "Impossible connecting to server\nRetry?";

    public static void main(String[] args){

        chatGUI = new ChatGUI();
        attemptConnection();

    }

    private static void attemptConnection(){
        try {
            Client client = new Client();
            client.setReceiveMessageInterface(chatGUI);
            chatGUI.setSendMessageInterface(client);
        } catch (IOException e) {
            System.out.println("Impossible connecting to server");
            if (retryConnection()) attemptConnection();
        }
    }

    private static boolean retryConnection(){
        int choice = JOptionPane.showConfirmDialog(
                chatGUI,
                RETRY_CONNECTION_MESSAGE,
                "Error connecting to server",
                JOptionPane.YES_NO_OPTION);
        return choice == 0;
    }

}
