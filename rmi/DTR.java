import java.rmi.*;

public interface DTR extends Remote {
    public String dtr(int x) throws RemoteException;
}