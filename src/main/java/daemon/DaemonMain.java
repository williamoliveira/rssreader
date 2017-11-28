package daemon;

import daemon.rmi.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.entities.schedule.ScheduleRepository;
import shared.repository.RepositoryFactory;

public class DaemonMain {
    private static Logger logger = LoggerFactory.getLogger(DaemonMain.class);

    public static void main(String[] args) {
        ScheduleRepository scheduleRepository
                = RepositoryFactory.getFactory().getScheduleRepository();

        Scheduler scheduler = new Scheduler(scheduleRepository);
        scheduler.refreshSchedules();

        Server server = new Server(scheduler);
        server.start();

        logger.info("Daemon started");
    }
}
