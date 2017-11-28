package daemon.rmi;

import daemon.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.rmi.DaemonRemoteInterface;

import java.rmi.RemoteException;

public class DaemonRemoteInterfaceImp implements DaemonRemoteInterface {

    private static Logger logger = LoggerFactory.getLogger(DaemonRemoteInterfaceImp.class);

    private Scheduler scheduler;

    public DaemonRemoteInterfaceImp(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void refreshSchedules() throws RemoteException {
        scheduler.refreshSchedules();

        logger.info("Called refreshSchedules from RMI");
    }
}
