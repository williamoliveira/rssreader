package shared.repository;

import shared.entities.schedule.ScheduleRepository;
import shared.entities.site.SiteRepository;
import shared.entities.term.TermRepository;
import shared.repository.jpa.RepositoryJpaFactory;
import shared.entities.post.PostRepository;

public abstract class RepositoryFactory {

    private static Class CURRENT_FACTORY_CLASS = RepositoryJpaFactory.class;

    public static RepositoryFactory getFactory() {
        try {
            return (RepositoryFactory) CURRENT_FACTORY_CLASS.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    public abstract PostRepository getPostRepository();
    public abstract SiteRepository getSiteRepository();
    public abstract TermRepository getTermRepository();
    public abstract ScheduleRepository getScheduleRepository();
}
