package server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Utente on 11/26/2016.
 */
public class ServerGUI {

    private static final String WINDOW_TITLE = "Server";
    private ServerStartStopInterface serverStartStopInterface;

    public ServerGUI(ServerStartStopInterface serverStartStopInterface) {
        this.serverStartStopInterface = serverStartStopInterface;

        JFrame mainFrame = new JFrame(WINDOW_TITLE);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.getContentPane().add(new MainInterface());
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private class MainInterface extends JPanel{

        private static final int FONT_SIZE = 25;

        public MainInterface() {
            this.setLayout(new FlowLayout());
            this.setBorder(new EmptyBorder(30, 30, 30, 30));
            JButton startButton = new JButton("Start");
            startButton.addActionListener(e -> attemptStartServer());
            startButton.setFont(new Font("serif", Font.PLAIN, FONT_SIZE));
            this.add(startButton);
            JButton stopButton = new JButton("Stop");
            stopButton.addActionListener(e -> attemptStopServer() );
            stopButton.setFont(new Font("serif", Font.PLAIN, FONT_SIZE));
            this.add(stopButton);
        }

        private void attemptStartServer(){
            try {
                serverStartStopInterface.startServer();
            } catch (IOException e){ e.printStackTrace(); }
        }

        private void attemptStopServer(){
            try {
                serverStartStopInterface.stopServer();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}
