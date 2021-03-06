package daemon.rmi;

import daemon.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.rmi.DaemonRemoteInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);

    private Scheduler scheduler;

    public Server(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void start(int port) {
        try {
            DaemonRemoteInterface ri = new DaemonRemoteInterfaceImp(scheduler);

            DaemonRemoteInterface stub =
                    (DaemonRemoteInterface) UnicastRemoteObject.exportObject(ri, port);

            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("DaemonRemoteInterface", stub);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void start() {
        start(9192);
    }
}
