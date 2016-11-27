package client;

import java.io.IOException;

/**
 * Created by Utente on 11/26/2016.
 */
public class ChatClient {

    public static void main(String[] args){

        try {
            Client client = new Client();
            ChatGUI chatGUI = new ChatGUI();
            client.setReceiveMessageInterface(chatGUI);
            chatGUI.setSendMessageInterface(client);
        } catch (IOException e) {
            System.out.println("Impossible to connect to server");
        }

    }

}
