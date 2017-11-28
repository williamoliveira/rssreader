package daemon;

import daemon.rmi.Server;
import shared.entities.schedule.ScheduleRepository;
import shared.repository.RepositoryFactory;

public class Main {
    public static void main(String[] args) {
        ScheduleRepository scheduleRepository
                = RepositoryFactory.getFactory().getScheduleRepository();

        Scheduler scheduler = new Scheduler(scheduleRepository);
        scheduler.refreshSchedules();

        Server server = new Server(scheduler);
        server.start();
    }
}
