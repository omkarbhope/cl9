import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DTRRemote extends UnicastRemoteObject implements DTR{
    public DTRRemote() throws RemoteException {
        super();
    }
    
    public String dtr(int x) throws RemoteException {
        float a = (float) x;
        a = a / 180.0f;
        return ("pi * " + a);
    }
}
