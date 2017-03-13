package test;

import client.Client;
import org.junit.BeforeClass;
import org.junit.Test;
import server.Server;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Tuly on 12/12/2016.
 */
public class ClientTest {

    @BeforeClass
    public static void startServer(){
        Server server = new Server();
        try {
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void StartClient(){
        Client client = null;
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(client);
    }

}
