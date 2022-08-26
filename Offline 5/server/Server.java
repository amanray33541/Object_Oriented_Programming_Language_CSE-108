import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.io.*;
import java.net.*;
import java.sql.ClientInfoStatus;

public class Server {

    public static int id=0;
    public static Socket[] connection = new Socket[6];

    public static void main(String[] args) throws Exception{
        ServerSocket welcomeSocket = new ServerSocket(17113);

        while(true){
            connection[id] = welcomeSocket.accept();
            System.out.println("Client Connected");
            ClientsRecord clients = new ClientsRecord(connection[id]);
            WorkerThread wt = new WorkerThread(connection[id],id);
            Thread t = new Thread(wt);
            t.start();
            id++;
        }
    }
}

class WorkerThread implements Runnable
{
    private BufferedReader inFromClient;
    private PrintWriter outToClient;
    private Socket connection;
    private static String[] clients;
    private static int id;

    public WorkerThread(Socket connection, int id) {
        this.connection = connection;
        this.id = id;
    }

    public void run(){
        int check=0;
        try {
            while(true){
                inFromClient = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                outToClient = new PrintWriter(connection.getOutputStream());
                String name = inFromClient.readLine();
                String password = inFromClient.readLine();
                LMessage logInText = new LMessage(name, password);
                boolean x=logInText.isValidAccount();
                String type = logInText.getType();
                outToClient.println(logInText.showLogInText());
                outToClient.flush();
                int i=0;
                while(true) {
                   if(x){
                       if(i==0)
                       {
                           outToClient.println("1");
                           outToClient.flush();
                           i++;
                       }
                       else
                       {
                           String sentence, modifiedSentence;
                           String clStr = "";
                           sentence = inFromClient.readLine();
                           if(sentence.equals("#s#"))
                           {
                               SMessage serverMessage = new SMessage(logInText.getUsername(),logInText.getPasseword(),sentence);
                               outToClient.println(serverMessage.showServerText());
                               outToClient.flush();
                               sentence = inFromClient.readLine();
                               serverMessage.setCommand(sentence);
                               if(serverMessage.getCommand().equals("show"))
                               {
                                   clients = serverMessage.showLoggedInClients();
                                   for(int k=0;k<logInText.getClientNumber();k++)
                                   {
                                       if(clients[k]==null)
                                           continue;
                                       clStr += clients[k]+" ";
                                       System.out.println(clStr);
                                   }
                                   outToClient.println(clStr);
                                   outToClient.flush();

                               }

                               else if(serverMessage.getCommand().equals("log out"))
                               {
                                   serverMessage.removeClient();
                                   check=1;
                                   break;
                               }

                               else
                               {
                                   System.out.println(serverMessage.getCommand());
                                   outToClient.println();
                               }
                           }
                           else if(sentence.equals("#b#"))
                           {
                                BMessage broadcastMessage = new BMessage(type,id);
                                System.out.println(type);
                                outToClient.println(broadcastMessage.showBroadcastText());
                                outToClient.flush();
                                sentence = inFromClient.readLine();
                                broadcastMessage.setText(sentence);
                                broadcastMessage.showBroadcasedtText();
                           }
                           else if(sentence.equals("#client#"))
                           {

                           }
                           else if (sentence.equals("log out"))
                           {
                               check=1;
                               break;
                           }
                           //modifiedSentence = sentence.toUpperCase();
                           //modifiedSentence = clStr.toUpperCase();
                           //System.out.println(modifiedSentence);

                       }
                   }
                   else{
                        outToClient.println("0");
                        outToClient.flush();
                        break;
                   }
                }
                System.out.println("lool");
                if (check==1)
                    break;
            }
        }

        catch (Exception e){
            System.out.println("Error!!");
            outToClient.println("Error!!");
            outToClient.flush();
        }
    }
}
