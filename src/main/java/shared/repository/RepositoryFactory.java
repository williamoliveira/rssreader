package shared.repository;

import shared.models.schedule.ScheduleRepository;
import shared.models.site.SiteRepository;
import shared.models.term.TermRepository;
import shared.repository.jpa.RepositoryJpaFactory;
import shared.models.post.PostRepository;

public abstract class RepositoryFactory {

    private static Class CURRENT_FACTORY_CLASS = RepositoryJpaFactory.class;

    private static RepositoryFactory repositoryFactoryInstance;

    public static RepositoryFactory getFactory() {
        try {
            if (repositoryFactoryInstance == null) {
                repositoryFactoryInstance = (RepositoryFactory) CURRENT_FACTORY_CLASS.newInstance();
            }

            return repositoryFactoryInstance;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    public abstract PostRepository getPostRepository();
    public abstract SiteRepository getSiteRepository();
    public abstract TermRepository getTermRepository();
    public abstract ScheduleRepository getScheduleRepository();
}
