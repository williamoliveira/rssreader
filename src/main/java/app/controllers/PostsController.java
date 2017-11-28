package app.controllers;

import app.views.MainView;
import app.views.tableModels.PostsTableModel;
import shared.entities.post.Post;
import shared.entities.post.PostRepository;
import shared.entities.site.Site;
import shared.entities.site.SiteRepository;
import shared.entities.term.Term;
import shared.entities.term.TermRepository;
import shared.rss.Verifier;

import java.util.List;

public class PostsController {
    private PostRepository postRepository;
    private SiteRepository siteRepository;
    private TermRepository termRepository;
    private MainView mainView;

    public PostsController(PostRepository postRepository,
                           SiteRepository siteRepository,
                           TermRepository termRepository,
                           MainView mainView) {
        this.postRepository = postRepository;
        this.siteRepository = siteRepository;
        this.termRepository = termRepository;
        this.mainView = mainView;

        setupComponents();
        refreshPostsTable();
    }

    private void setupComponents() {
        mainView.postsTable.setModel(new PostsTableModel());

        mainView.verifyNowButton.addActionListener((action) -> verifyNow());
    }

    private void refreshPostsTable() {
        List<Post> posts = postRepository.fetchMany();
        ((PostsTableModel)mainView.postsTable.getModel()).setResources(posts);
    }

    private void verifyNow() {
        List<Site> sites = siteRepository.fetchMany();
        List<Term> terms = termRepository.fetchMany();
        List<Post> posts = Verifier.verify(sites, terms);

        posts.stream()
                .filter(post -> !postRepository.existsByUrl(post.getUrl()))
                .forEach(post -> postRepository.transaction(() -> postRepository.save(post)));
        refreshPostsTable();
    }

}
