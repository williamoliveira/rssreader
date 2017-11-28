package app.controllers;

import app.views.MainView;
import app.views.tableModels.PostsTableModel;
import shared.entities.post.Post;
import shared.entities.post.PostRepository;
import shared.entities.site.SiteRepository;
import shared.entities.term.TermRepository;
import shared.services.rss.FetchAndSave;

import java.util.List;

public class PostsController {
    private PostRepository postRepository;
    private SiteRepository siteRepository;
    private TermRepository termRepository;
    private MainView mainView;
    private FetchAndSave fetchAndSave;

    public PostsController(PostRepository postRepository,
                           SiteRepository siteRepository,
                           TermRepository termRepository,
                           MainView mainView) {
        this.postRepository = postRepository;
        this.siteRepository = siteRepository;
        this.termRepository = termRepository;
        this.mainView = mainView;
        fetchAndSave = new FetchAndSave(postRepository, siteRepository, termRepository);

        setupComponents();
        refreshPostsTable();
    }

    private void setupComponents() {
        mainView.postsTable.setModel(new PostsTableModel());

        mainView.verifyNowButton.addActionListener((action) -> verifyNow());
    }

    public void refreshPostsTable() {
        List<Post> posts = postRepository.fetchMany();
        ((PostsTableModel)mainView.postsTable.getModel()).setResources(posts);
    }

    private void verifyNow() {
        fetchAndSave.fetchAndSave();
        refreshPostsTable();
    }

}
