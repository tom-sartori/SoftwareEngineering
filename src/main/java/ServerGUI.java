import common.ChatIF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerGUI extends JFrame implements ChatIF {

    EchoServer echoServer;
    private JTextArea jTextArea;

    public ServerGUI() {
        super("SimpleChat 4 | Server"); // ou setTitle("...");

        this.echoServer = new EchoServer(5555, this);


        setSize(1920, 1080); // ou setBounds(...);

        Container contentPane = getContentPane();

        /**
         *  NORTH PART LABEL "Welcome to simpleChat 4"
         */
        JPanel topLabel = new JPanel();

        JLabel topMessage = new JLabel("Welcome to simpleChat 4 !! :)");
        topLabel.add(topMessage);

        contentPane.add(topLabel, BorderLayout.NORTH);

        /**
         *  CENTER PART TEXT AREA WHERE DATA WILL BE WRITTEN WITH SERVER RESPONSES
         */

        this.jTextArea = new JTextArea();
        contentPane.add(jTextArea, BorderLayout.CENTER);  // Text area middle.

        /**
         *  SOUTH PANE WITH TEXTAREA , SEND BUTTON AND QUIT BUTTON
         */

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(3,1));

        JPanel sendMessagesPanel = new JPanel();
        sendMessagesPanel.setLayout(new GridLayout(1,2));

        JTextArea sendMessageTextArea = new JTextArea();

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                echoServer.handleMessageFromServerUI(sendMessageTextArea.getText());
                sendMessageTextArea.setText("");
            }
        });

        JLabel writeMessageLabel = new JLabel("Write your message below :");
        southPanel.add(writeMessageLabel);

        sendMessagesPanel.add(sendMessageTextArea);
        sendMessagesPanel.add(sendButton);


        southPanel.add(sendMessagesPanel);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                echoServer.handleMessageFromServerUI("#close");
                System.exit(0);
            }
        });

        southPanel.add(quitButton);

        contentPane.add(southPanel, BorderLayout.SOUTH);

        jTextArea.append("Bienvenue sur le serveur, envoyez #start pour lancer l'écoute de nouveaux utilisateurs.\nUtilisez #help pour voir les commandes disponibles !\n");

        setVisible(true); // ou show(), // affiche la fenêtre
    }

    public void display(String msg){
        this.jTextArea.append(msg + "\n");
    }

    public EchoServer getEchoServer() {
        return echoServer;
    }


    /**
     * This method is responsible for the creation of
     * the server instance (there is no UI in this phase).
     *
     * @param args [0] The port number to listen on.  Defaults to 5555
     *          if no argument is entered.
     */
    public static void main(String[] args)
    {
        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = EchoServer.DEFAULT_PORT; //Set port to 5555
        }

        ServerGUI serverConsole = new ServerGUI();
    }
}

