package app;

import app.controllers.MainController;
import shared.entities.post.PostRepository;
import shared.entities.site.SiteRepository;
import shared.entities.term.TermRepository;
import shared.repository.RepositoryFactory;

public class Main {
    public static void main(String[] args) {

        PostRepository postRepository = RepositoryFactory.getFactory().getPostRepository();
        SiteRepository siteRepository = RepositoryFactory.getFactory().getSiteRepository();
        TermRepository termRepository = RepositoryFactory.getFactory().getTermRepository();
        
        MainController mainController = new MainController(
                postRepository,
                siteRepository,
                termRepository
        );
        mainController.show();
    }
}
