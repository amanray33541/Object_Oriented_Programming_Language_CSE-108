public class SMessage extends LMessage{
    private String command;
    private String text;

    public SMessage(String username, String password,String command) {
        super(username, password);
        this.command = command;
    }


    public String[] showLoggedInClients()
    {
        return clientName;
    }

    public String showServerText()
    {
        return "type 'show' or 'log out'";
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void removeClient()
    {
        for(int k=0;k<clientNumber;k++)
        {
            if(username.equals(clientName[k]))
            {
                clientName[k]=null;
                break;
            }
        }
    }
}
