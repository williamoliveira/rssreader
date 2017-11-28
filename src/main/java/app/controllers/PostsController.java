package app.controllers;

import app.views.MainView;
import app.views.tableModels.PostsTableModel;
import shared.entities.post.Post;
import shared.entities.post.PostRepository;
import shared.entities.site.SiteRepository;
import shared.entities.term.TermRepository;
import shared.services.rss.FetchAndSave;

import java.awt.*;
import java.net.URL;
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
        mainView.openButton.addActionListener((action) -> open());
        mainView.excluirSelecionadoButton.addActionListener((action) -> deletePostHandler());
    }

    public void refreshPostsTable() {
        List<Post> posts = postRepository.fetchMany();
        ((PostsTableModel)mainView.postsTable.getModel()).setResources(posts);
    }

    private void verifyNow() {
        fetchAndSave.fetchAndSave();
        refreshPostsTable();
    }

    private void open() {
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

    public void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
