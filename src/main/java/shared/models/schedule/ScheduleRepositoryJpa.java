package shared.models.schedule;

import shared.repository.jpa.RepositoryJpa;

import javax.persistence.EntityManager;

public class ScheduleRepositoryJpa extends RepositoryJpa<Schedule> implements ScheduleRepository {

    public ScheduleRepositoryJpa(EntityManager manager) {
        super(manager, Schedule.class);
    }
}
