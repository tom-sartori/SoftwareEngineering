import client.ChatClient;
import common.ChatIF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.awt.*;
import java.io.*;
import java.util.Objects;

import client.ChatClient;
import common.ChatIF;

import javax.swing.*;
import javax.swing.event.AncestorListener;

import static java.lang.Thread.sleep;

// This file contains material supporting section 3.7 of the textbook:
// "Object-Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some the code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientGUI extends JFrame implements ChatIF {
        //Class variables *************************************************

        /**
         * The default port to connect on.
         */
        final public static int DEFAULT_PORT = 5555;

        //Instance variables **********************************************

        /**
         * The instance of the client that created this ConsoleChat.
         */
        ChatClient client;

        private JTextArea jTextArea;

        //Constructors ****************************************************

        /**
         * Constructs an instance of the ClientConsole UI.
         *
         * @param host The host to connect to.
         * @param port The port to connect on.
         */
        public ClientGUI(String host, int port) {
            super("SimpleChat 4"); // ou setTitle("...");
            try {
                client = new ChatClient(host, port, this);
            }
            catch(IOException exception) {
                System.out.println("Error: Can't setup connection!" + " Terminating client.");
                System.exit(1);
            }


            setSize(600, 600); // ou setBounds(...);

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
            JScrollPane scrollPane = new JScrollPane(this.jTextArea);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            contentPane.add(scrollPane, BorderLayout.CENTER);  // Text area middle.

            /**
             *  SOUTH PANE WITH TEXTAREA , SEND BUTTON AND QUIT BUTTON
             */

            JPanel southPanel = new JPanel();
            southPanel.setLayout(new GridLayout(3,1));

            JPanel sendMessagesPanel = new JPanel();
            sendMessagesPanel.setLayout(new GridLayout(1,2));

            JTextField sendMessageTextField = new JTextField();
            sendMessageTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    client.handleMessageFromClientUI(sendMessageTextField.getText());
                    sendMessageTextField.setText("");
                }
            });

            JButton sendButton = new JButton("Send");
            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    client.handleMessageFromClientUI(sendMessageTextField.getText());
                    sendMessageTextField.setText("");
                }
            });

            JLabel writeMessageLabel = new JLabel("Write your message below :");
            southPanel.add(writeMessageLabel);

            sendMessagesPanel.add(sendMessageTextField);
            sendMessagesPanel.add(sendButton);


            southPanel.add(sendMessagesPanel);

            JButton quitButton = new JButton("Quit");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    client.quit();
                }
            });

            southPanel.add(quitButton);

            contentPane.add(southPanel, BorderLayout.SOUTH);

            jTextArea.append("Bienvenue sur notre application de chat ! Pensez à vous login avec la commande #login <pseudo> afin de pouvoir utiliser le chat, sans celà vous serez automatiquement déconnecté.\nUtilisez #help pour voir les commandes disponibles !\n");
            client.handleMessageFromClientUI("#help");

            setVisible(true); // ou show(), // affiche la fenêtre
        }


        //Instance methods ************************************************

        /**
         * This method overrides the method in the ChatIF interface.  It
         * displays a message onto the screen.
         *
         * @param message The string to be displayed.
         */
        public void display(String message){
            this.jTextArea.append(message + "\n");
        }

        //Class methods ***************************************************

        /**
         * This method is responsible for the creation of the Client UI.
         *
         * @param args [0] The host to connect to.
         */
        public static void main(String[] args) {

            String host;
            int port = 0;  //The port number

            try {
                host = args[0];
            }
            catch(ArrayIndexOutOfBoundsException e) {
                host = "localhost";
            }
            ClientGUI chat = new ClientGUI(host, DEFAULT_PORT);
        }

    }

