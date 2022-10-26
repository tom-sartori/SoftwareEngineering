import common.ChatIF;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerConsole implements ChatIF {

    EchoServer echoServer;

    public ServerConsole() {
        this.echoServer = new EchoServer(5555, this);
    }

    @Override
    public void display(String message) {
        System.out.println(message);
    }

    public EchoServer getEchoServer() {
        return echoServer;
    }

    /**
     * This method waits for input from the console.  Once it is
     * received, it sends it to the client's message handler.
     */
    public void accept() {
        try {
            BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                message = fromConsole.readLine();
                echoServer.handleMessageFromServerUI(message);
            }
        }
        catch (Exception ex) {
            System.out.println("Unexpected error while reading from console!");
        }
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

        ServerConsole serverConsole = new ServerConsole();

        try
        {
            sv.listen(); //Start listening for connections
            serverConsole.accept();
        }
        catch (Exception ex)
        {
            serverConsole.display("ERROR - Could not listen for clients!");
        }
    }
}
