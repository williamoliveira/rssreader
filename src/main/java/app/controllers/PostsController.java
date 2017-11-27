package app.controllers;

import app.views.MainView;
import app.views.tableModels.PostsTableModel;
import shared.entities.post.Post;
import shared.entities.post.PostRepository;

import java.util.List;

public class PostsController {
    private PostRepository postRepository;
    private MainView mainView;

    public PostsController(PostRepository postRepository, MainView mainView) {
        this.postRepository = postRepository;
        this.mainView = mainView;

        setupComponents();
        refreshPostsTable();
    }

    private void setupComponents() {
        mainView.postsTable.setModel(new PostsTableModel());
    }

    private void refreshPostsTable() {
        List<Post> posts = postRepository.fetchMany();
        ((PostsTableModel)mainView.postsTable.getModel()).setResources(posts);
    }
}
