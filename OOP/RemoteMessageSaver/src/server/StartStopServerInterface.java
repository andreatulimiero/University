package server;

import java.io.IOException;

/**
 * Created by Utente on 11/30/2016.
 */
public interface StartStopServerInterface {
    void startServer() throws IOException;

    void stopServer();
}
