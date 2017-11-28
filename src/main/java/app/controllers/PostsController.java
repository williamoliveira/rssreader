package app.controllers;

import app.views.MainView;
import app.views.tableModels.PostsTableModel;
import shared.models.post.Post;
import shared.models.post.PostRepository;
import shared.models.site.SiteRepository;
import shared.models.term.TermRepository;
import shared.services.rss.FetchAndSave;

import java.awt.*;
import java.net.URL;
import java.util.List;

public class PostsController {
    private PostRepository postRepository;
    private MainView mainView;
    private FetchAndSave fetchAndSave;

    public PostsController(PostRepository postRepository,
                           SiteRepository siteRepository,
                           TermRepository termRepository,
                           MainView mainView) {
        this.postRepository = postRepository;
        this.mainView = mainView;
        fetchAndSave = new FetchAndSave(postRepository, siteRepository, termRepository);

        setupComponents();
        refreshPostsTable();
    }

    private void setupComponents() {
        mainView.postsTable.setModel(new PostsTableModel());

        mainView.verifyNowButton.addActionListener((action) -> fetchNowHandler());
        mainView.postsOpenButton.addActionListener((action) -> openPostHandler());
        mainView.postsDeleteButton.addActionListener((action) -> deletePostHandler());
    }

    public void refreshPostsTable() {
        List<Post> posts = postRepository.fetchMany();
        ((PostsTableModel)mainView.postsTable.getModel()).setResources(posts);
    }

    private void fetchNowHandler() {
        fetchAndSave.fetchAndSave();
        refreshPostsTable();
    }

    private void openPostHandler() {
        Post post = getSelectedPost();

        openWebpage(post.getUrl());
    }

    private void deletePostHandler() {
        Post post = getSelectedPost();

        postRepository.transaction(() -> postRepository.delete(post));
        refreshPostsTable();
    }
    
    private Post getSelectedPost() {
        int index = mainView.postsTable.getSelectedRow();

        return ((PostsTableModel)mainView.postsTable.getModel())
                .getResources()
                .get(index);
    }

    private void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
