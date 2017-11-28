package shared.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DaemonRemoteInterface extends Remote {
    public void refreshSchedules() throws RemoteException;
}
