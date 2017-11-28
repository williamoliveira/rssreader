package shared.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AppRemoteInterface extends Remote {
    public void refreshPostsTable() throws RemoteException;
}
