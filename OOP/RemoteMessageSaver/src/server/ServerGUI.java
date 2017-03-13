package server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Utente on 11/30/2016.
 */
public class ServerGUI extends JFrame {

    private static final String WINDOW_TITLE = "Server";

    private StartStopServerInterface startStopServerInterface;

    public ServerGUI() throws HeadlessException {
        super(WINDOW_TITLE);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.add(new OnOffBar());

        this.pack();
        this.setVisible(true);
    }

    public void setStartStopServerInterface(StartStopServerInterface startStopServerInterface) {
        this.startStopServerInterface = startStopServerInterface;
    }

    private class OnOffBar extends JPanel{

        public OnOffBar() {
            super();
            this.setLayout(new FlowLayout());
            OnOffBarButton onButton = new OnOffBarButton("On");
            OnOffBarButton offButton = new OnOffBarButton("Off");
            offButton.setEnabled(false);
            onButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    try {
                        startStopServerInterface.startServer();
                        offButton.setEnabled(true);
                        onButton.setEnabled(false);
                    } catch (IOException e) {
                        System.out.println("Impossible to start the server");
                    }
                }
            });
            offButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startStopServerInterface.stopServer();
                    offButton.setEnabled(false);
                    onButton.setEnabled(true);
                }
            });
            this.add(onButton);
            this.add(offButton);
        }

        class OnOffBarButton extends JButton{

            public OnOffBarButton(String text) {
                super(text);
                this.setPreferredSize(new Dimension(120, 80));
                this.setFont(new Font("sans-serif", Font.PLAIN, 30));
                this.setBorder(new EmptyBorder(10, 10, 10, 10));
                this.setBackground(Color.WHITE);
                this.setForeground(Color.DARK_GRAY);
            }
        }
    }
}
