package daemon;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.entities.schedule.Schedule;
import shared.entities.schedule.ScheduleRepository;

import java.util.List;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Scheduler {

    private static Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private ScheduleRepository scheduleRepository;
    private org.quartz.Scheduler scheduler;

    public Scheduler(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;

        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            scheduler = sf.getScheduler();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void refreshSchedules() {
        List<Schedule> schedules = scheduleRepository.fetchMany();
        schedule(schedules);
    }

    private void schedule(List<Schedule> schedules) {
        JobDetail job = newJob(RssJob.class).build();

        TriggerBuilder builder = newTrigger().forJob(job);

        schedules.forEach(
                schedule -> builder.withSchedule(
                        dailyAtHourAndMinute(
                                schedule.getHour(),
                                schedule.getMinutes()
                        )
                )
        );

        Trigger trigger = builder.build();

        scheduleJob(job, trigger);
    }

    private void scheduleJob(JobDetail job, Trigger trigger) {
        try {
            scheduler.clear();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
