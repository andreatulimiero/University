package client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Utente on 11/26/2016.
 */
public class ChatGUI implements ReceiveMessageInterface{

    private static final String WINDOW_TITLE = "Client";

    private SendMessageInterface sendMessageInterface;
    private MainInterface mainInterface;

    public ChatGUI() {

        JFrame mainFrame = new JFrame(WINDOW_TITLE);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainInterface = new MainInterface();
        mainFrame.getContentPane().add(mainInterface);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(500, 500);
        mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
        mainFrame.setVisible(true);
    }

    public void setSendMessageInterface(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void receivedMessage(String message) {
        mainInterface.addMessage(message);
    }

    private class MainInterface extends JPanel{

        private static final int TEXT_SIZE = 20;

        private JTextArea messagesLabel;

        public MainInterface() {
            this.setLayout(new BorderLayout());
            messagesLabel = new JTextArea();
            messagesLabel.setEditable(false);
            messagesLabel.setFont(new Font("sans-serif", Font.PLAIN, TEXT_SIZE));
            messagesLabel.setText("Text received here !");
            messagesLabel.setBorder(new EmptyBorder(30, 30, 30, 30));
            this.add(messagesLabel, BorderLayout.NORTH);
            this.add(new MessageBar(), BorderLayout.SOUTH);
        }

        protected void addMessage(String message){
            messagesLabel.setText(messagesLabel.getText()+ "\n" + message);
        }

        private class MessageBar extends JPanel{

            private JTextField messageTextField;
            private JButton sendButton;
            private static final int TEXT_SIZE = 20;

            public MessageBar() {
                this.setLayout(new GridLayout(1, 2));

                messageTextField = new JTextField();
                messageTextField.setFont(new Font("sans-serif", Font.PLAIN, TEXT_SIZE));
                messageTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
                this.add(messageTextField, 0);
                sendButton = new JButton("Send");
                sendButton.addActionListener(e -> attemptSendMessage());
                this.add(sendButton, 1);
            }

            private void attemptSendMessage(){
                sendMessageInterface.sendMessage(messageTextField.getText());
                messageTextField.setText("");
            }
        }

    }
}
