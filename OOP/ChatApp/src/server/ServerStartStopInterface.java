package server;

import java.io.IOException;

/**
 * Created by Utente on 11/26/2016.
 */
public interface ServerStartStopInterface {
    void startServer() throws IOException;
    void stopServer() throws IOException;
}
