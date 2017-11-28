package shared.repository.jpa;

import shared.entities.schedule.ScheduleRepository;
import shared.entities.schedule.ScheduleRepositoryJpa;
import shared.entities.site.SiteRepository;
import shared.entities.site.SiteRepositoryJpa;
import shared.entities.term.TermRepository;
import shared.entities.term.TermRepositoryJpa;
import shared.repository.RepositoryFactory;
import shared.entities.post.PostRepository;
import shared.entities.post.PostRepositoryJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoryJpaFactory extends RepositoryFactory {

    private EntityManager manager;

    public RepositoryJpaFactory() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        manager = factory.createEntityManager();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
//        manager.close();
    }

    public PostRepository getPostRepository() {
        return new PostRepositoryJpa(manager);
    }

    @Override
    public SiteRepository getSiteRepository() {
        return new SiteRepositoryJpa(manager);
    }

    @Override
    public TermRepository getTermRepository() {
        return new TermRepositoryJpa(manager);
    }

    @Override
    public ScheduleRepository getScheduleRepository() {
        return new ScheduleRepositoryJpa(manager);
    }

}
