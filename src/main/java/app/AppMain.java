package app;

import app.controllers.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.models.post.PostRepository;
import shared.models.schedule.ScheduleRepository;
import shared.models.site.SiteRepository;
import shared.models.term.TermRepository;
import shared.repository.RepositoryFactory;

public class AppMain {
    private static Logger logger = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args) {

        PostRepository postRepository
                = RepositoryFactory.getFactory().getPostRepository();
        SiteRepository siteRepository
                = RepositoryFactory.getFactory().getSiteRepository();
        TermRepository termRepository
                = RepositoryFactory.getFactory().getTermRepository();
        ScheduleRepository scheduleRepository
                = RepositoryFactory.getFactory().getScheduleRepository();
        
        MainController mainController = new MainController(
                postRepository,
                siteRepository,
                termRepository,
                scheduleRepository
        );

        mainController.show();

        logger.info("App started");
    }
}
