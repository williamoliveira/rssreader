package daemon.rmi;

import daemon.Scheduler;
import shared.rmi.DaemonRemoteInterface;

import java.rmi.RemoteException;

public class DaemonRemoteInterfaceImp implements DaemonRemoteInterface {

    private Scheduler scheduler;

    public DaemonRemoteInterfaceImp(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void refreshSchedules() throws RemoteException {
        scheduler.refreshSchedules();
    }
}
