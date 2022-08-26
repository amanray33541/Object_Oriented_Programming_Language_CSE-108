import java.io.PrintWriter;
import java.net.Socket;

public class BMessage extends ClientsRecord {
    private String text;
    private String type;
    private int client;

    //public BMessage(String text)
    {
        this.text = text;
    }

    public BMessage(Socket s) {
        super(s);
    }

    public BMessage(String type, int client)
    {
        this.type = type;
        this.client = client;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String showBroadcastText()
    {
        return "Type a message. This will be forwarded to others(only for admins.)";
    }

    public void showBroadcasedtText()
    {
        try{
            for(int i=0;i<=id;i++)
            {
                if(type.equals("admin"))
                {
                    PrintWriter outToClient = new PrintWriter(connection[i].getOutputStream());
                    if(i==client)
                    {
                        outToClient.println("successful");
                        outToClient.flush();
                    }
                    else
                    {
                        outToClient.println(text);
                        outToClient.flush();
                        System.out.println(text);
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("error");
        }
    }
}
