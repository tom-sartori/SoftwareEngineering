// This file contains material supporting section 3.7 of the textbook:// "Object Oriented Software Engineering" and is issued under the open-source// license found at www.lloseng.com package client;import common.ChatIF;import constant.Commands;import model.Command;import ocsf.client.ObservableClient;import java.io.IOException;import java.util.Observable;import java.util.Observer;/** * This class overrides some of the methods defined in the abstract * superclass in order to give more functionality to the client. * * @author Dr Timothy C. Lethbridge * @author Dr Robert Lagani&egrave; * @author Fran&ccedil;ois B&eacute;langer * @version July 2000 */public class ChatClient implements Observer {  //Instance variables **********************************************  /**   * The interface type variable.  It allows the implementation of    * the display method in the client.   */  private final ChatIF clientUI;  private ObservableClient observableClient;  private boolean logged;  //Constructors ****************************************************  /**   * Constructs an instance of the chat client.   *   * @param host The server to connect to.   * @param port The port number to connect on.   * @param clientUI The interface type variable.   */  public ChatClient(String host, int port, ChatIF clientUI) throws IOException {    this.clientUI = clientUI;    this.observableClient = new ObservableClient(host,port);    this.observableClient.openConnection();    this.logged = false;  }  //Instance methods ************************************************  /**   * This method handles all data that comes in from the server.   *   * @param msg The message from the server.   */  public void handleMessageFromServer(Object msg)  {    clientUI.display(msg.toString());  }  /**   * This method handles all data coming from the UI               *   * @param message The message from the UI.       */  public void handleMessageFromClientUI(String message) {    try {      if (message.startsWith("#")){        System.out.println("Commande détectée. ");        handleCommand(new Command(message));      }      else{        this.observableClient.sendToServer(message);      }    }    catch(IOException e) {      clientUI.display("Could not send message to server.  Terminating client.");      quit();    }  }  /**   * This method terminates the client.   */  public void quit() {    try {      this.observableClient.closeConnection();    }    catch(IOException e) {      e.printStackTrace();    }    System.exit(0);  }//  protected void connectionClosed() {//    this.observableClient.connectionClosed();//    connectionException( new Exception("The connexion to server has been closed. "));//  }////  protected void connectionException(Exception exception) {//    this.observableClient.connectionException(exception);//    clientUI.display(exception.getMessage());//  }  private void handleCommand(Command command) {    switch (command.getCommand()) {      //"#logoff - termine la connexion avec le serveur, mais n'arrête pas le client.      case Commands.LOGOFF:        try {          this.observableClient.sendToServer("#logoff");          this.observableClient.closeConnection();        } catch (IOException e) {          System.out.println(e.getMessage());        }        break;      //"#quit - arrête l'exécution du client. Assurez vous que la connexion avec le serveur est arrêtée avant l'arrêt du programme.      case Commands.QUIT:        try {          this.observableClient.sendToServer("#logoff");          System.exit(0);        } catch (IOException e) {          e.printStackTrace();        }        break;      //"#sethost <host> - appel de la méthode setHost du client. L'appel est autorisé seulement si le client est déconnecté.      case Commands.SETHOST:        this.observableClient.setHost(command.getArgumentList().get(0));        break;      //"#setport <port> - appel de la méthode setPort avec les même contraintes que celles de #sethost.      case Commands.SETPORT:        this.observableClient.setPort(Integer.parseInt(command.getArgumentList().get(0)));        break;      //"#login - connecte le client au serveur. La commande est autorisée seulement si le client n'est pas connecté. Sinon un message d'erreur s'affiche.      case Commands.LOGIN:        if (!isLogged()){          try {            this.observableClient.openConnection();            this.observableClient.sendToServer(command.getMessage());            setLogged(true);          }          catch (IOException e) {            e.printStackTrace();          }        }        else {          clientUI.display("You already are logged on the server. ");        }        break;      //"#gethost      case Commands.GETHOST:        clientUI.display("the host is: " + this.observableClient.getHost());        //"#getport"      case Commands.GETPORT:        clientUI.display("the port is: " + this.observableClient.getPort());    }  }  public boolean isLogged() {    return logged;  }  public void setLogged(boolean logged) {    this.logged = logged;  }  @Override  public void update(Observable o, Object arg) {    if(arg instanceof String){      switch (arg.toString()){        case ObservableClient.CONNECTION_CLOSED:          clientUI.display("Connection has been closed by the server");          break;        case ObservableClient.CONNECTION_ESTABLISHED:          clientUI.display("Connection has been established successfully");          break;        default:          break;      }    }    else if(arg instanceof Exception){      clientUI.display(((Exception) arg).getMessage());    }    else{      this.handleMessageFromServer(arg.toString());    }  }}//End of ChatClient class