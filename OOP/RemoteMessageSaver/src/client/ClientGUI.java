package client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Utente on 11/30/2016.
 */
public class ClientGUI extends JFrame implements AuthenticateInterface {

    private static final String WINDOW_TITLE = "Client";

    private SendMessageInterface sendMessageInterface;

    public ClientGUI() {
        super(WINDOW_TITLE);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new StatusBar());
        this.add(new SendBar());

        this.pack();
        this.setVisible(true);
    }

    public void setSendMessageInterface(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void authenticated() {

    }

    private class StatusBar extends JPanel{

        public StatusBar() {
        }
    }

    private class SendBar extends JPanel{

        public SendBar() {
            this.setLayout(new FlowLayout());

            StatusBarTextField textField = new StatusBarTextField();
            this.add(textField);
            SendBarButton button = new SendBarButton("Send");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (textField.getText().trim().isEmpty()) return;
                    sendMessageInterface.sendMessage(textField.getText());
                    textField.setText("");
                }
            });
            this.add(button);
        }

        private class StatusBarTextField extends JTextField{

            public StatusBarTextField() {
                super();
                this.setFont(new Font("sans-serif", NORMAL, 40));
                this.setBorder(new EmptyBorder(10, 10, 10, 10));
                this.setPreferredSize(new Dimension(400, 100));
            }

        }

        private class SendBarButton extends JButton{

            public SendBarButton(String text) {
                super(text);

                this.setFont(new Font("sans-serif", NORMAL, 30));
                this.setBackground(Color.WHITE);
                this.setForeground(Color.DARK_GRAY);
                this.setBorder(new EmptyBorder(10, 10, 10, 10));
                this.setPreferredSize(new Dimension(200, 100));
            }
        }
    }

}
