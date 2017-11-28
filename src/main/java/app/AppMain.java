package app;

import app.controllers.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.entities.post.PostRepository;
import shared.entities.schedule.ScheduleRepository;
import shared.entities.site.SiteRepository;
import shared.entities.term.TermRepository;
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

        logger.info("App started");

        mainController.show();
    }
}
