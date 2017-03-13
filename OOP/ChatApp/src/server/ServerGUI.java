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

        private Button startButton, stopButton;

        private static final int FONT_SIZE = 30;

        public MainInterface() {
            this.setLayout(new FlowLayout());
            this.setBorder(new EmptyBorder(30, 30, 30, 30));
            startButton = new Button("Start");
            startButton.addActionListener(e -> attemptStartServer());
            this.add(startButton);
            stopButton = new Button("Stop");
            stopButton.addActionListener(e -> attemptStopServer() );
            stopButton.setEnabled(false);
            this.add(stopButton);
        }

        private void attemptStartServer(){
            try {
                serverStartStopInterface.startServer();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            } catch (IOException e){ e.printStackTrace(); }
        }

        private void attemptStopServer(){
            try {
                serverStartStopInterface.stopServer();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            } catch (IOException e) { e.printStackTrace(); }
        }

        private class Button extends JButton{

            public Button(String text) {
                super(text);
                this.setFont(new Font("sans-serif", Font.PLAIN, FONT_SIZE));
                this.setBackground(new Color(0, 105, 92));
                this.setForeground(Color.WHITE);
            }
        }
    }
}
