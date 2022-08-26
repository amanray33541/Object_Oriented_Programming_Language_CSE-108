import java.net.Socket;

public class ClientsRecord {
    protected static Socket[] connection = new Socket[6];
    protected static int id=0;

    public ClientsRecord(Socket s) {
        connection[id] = s;
        id++;

    }

    public ClientsRecord() {

    }
}


