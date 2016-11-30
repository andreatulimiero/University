package client;

import java.io.IOException;

/**
 * Created by Tuly on 11/29/2016.
 */
public class ClientApp {

    public static void main(String[] args){
        try {
            Client client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
