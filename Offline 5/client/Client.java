import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private static Socket connection = null;
    private static BufferedReader inFromServer = null;
    private static PrintWriter outToServer = null;

    public static void main(String[] args) throws Exception {
        System.out.println("baaaaal");
        connection = new Socket("localhost",17113);
        inFromServer = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        outToServer = new PrintWriter(connection.getOutputStream(),true);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String sentence , modifiedSentence;
        while(true){
            sentence=  input.readLine();
            outToServer.println(sentence);
            if(sentence.equals("bye")) break;
            modifiedSentence = inFromServer.readLine() + '\n';
            System.out.print(modifiedSentence);
        }
        cleanUp();
    }

    private static void cleanUp() throws Exception
    {
        connection.close();
        inFromServer.close();
        outToServer.close();
    }

}



