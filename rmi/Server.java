import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server extends DTRRemote{

    public Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException {
        DTR stub = new DTRRemote();
        try {
            Naming.rebind("rmi://localhost:5000/calculate",stub);
            System.err.println("Server Started !!!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
    }
    
}
