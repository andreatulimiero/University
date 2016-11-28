package client;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
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
        mainFrame.pack();
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

        private JTextArea messagesTextArea;

        public MainInterface() {
            this.setLayout(new BorderLayout());
            messagesTextArea = new JTextArea();
            messagesTextArea.setEditable(false);
            messagesTextArea.setFont(new Font("sans-serif", Font.PLAIN, TEXT_SIZE));
            messagesTextArea.setBorder(new EmptyBorder(30, 30, 30, 30));
            messagesTextArea.setBackground(new Color(100, 255, 218));
            messagesTextArea.setPreferredSize(new Dimension(600, 600));
            this.add(messagesTextArea, BorderLayout.NORTH);
            this.add(new MessageBar(), BorderLayout.SOUTH);
        }

        protected void addMessage(String message){
            messagesTextArea.setText(messagesTextArea.getText()+ "\n" + message);
        }

        private class MessageBar extends JPanel{

            private JTextField messageTextField;
            private JButton sendButton;
            private static final int TEXT_SIZE = 20;

            public MessageBar() {
                this.setLayout(new FlowLayout());

                messageTextField = new JTextField();
                messageTextField.setFont(new Font("sans-serif", Font.PLAIN, TEXT_SIZE));
                messageTextField.setPreferredSize(new Dimension(500, 50));
                messageTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
                this.add(messageTextField, 0);
                sendButton = new JButton("Send");
                sendButton.setPreferredSize(new Dimension(100, 50));
                sendButton.addActionListener(e -> attemptSendMessage());
                sendButton.setFont(new Font("sans-serif", Font.PLAIN, TEXT_SIZE));
                sendButton.setBackground(new Color(224, 242, 241));
                sendButton.setForeground(new Color(0, 77, 64));
                this.add(sendButton, 1);
            }

            private void attemptSendMessage(){
                sendMessageInterface.sendMessage(messageTextField.getText());
                messageTextField.setText("");
            }
        }

    }
}
