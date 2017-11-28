package shared.repository.jpa;

import shared.models.schedule.ScheduleRepository;
import shared.models.schedule.ScheduleRepositoryJpa;
import shared.models.site.SiteRepository;
import shared.models.site.SiteRepositoryJpa;
import shared.models.term.TermRepository;
import shared.models.term.TermRepositoryJpa;
import shared.repository.RepositoryFactory;
import shared.models.post.PostRepository;
import shared.models.post.PostRepositoryJpa;

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
