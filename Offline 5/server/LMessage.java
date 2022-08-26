import java.io.File;
import java.util.Scanner;
import java.util.stream.Stream;

public class LMessage {
    protected String username;
    private String password;
    private String type;
    protected static String[] clientName =new String[6];
    protected static int clientNumber=0;
    private boolean validity = false;

    public LMessage(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPasseword()
    {
        return password;
    }

    public void setPasseword(String passeword) {
        this.password = passeword;
    }

    public boolean isValidAccount()
    {
        try{
            Scanner file = new Scanner(new File("C:\\Users\\Lenovo\\IdeaProjects\\Server\\src\\record.txt"));
            int i=0;
            while(file.hasNext()){
                String n = file.next();
                String p = file.next();
                type = file.next();

                if(username.equals(n) && password.equals(p)){
                    clientName[clientNumber++] = username;
                    validity = true;
                    break;
                }
            }
            return validity;
        } catch (Exception e){
            System.out.println("Error loading file!!");
            return validity;
        }
    }

    public String showLogInText()
    {
        if(validity) return "Logged In";
        else return "Couldn't find account" ;
    }

    public String getType() {
        return type;
    }

    public static int getClientNumber() {
        return clientNumber;
    }

    public static String[] getClientName() {
        return clientName;
    }
}
