package app.controllers;

import app.views.MainView;
import shared.entities.post.PostRepository;
import shared.entities.site.SiteRepository;
import shared.entities.term.TermRepository;

import javax.swing.*;

public class MainController {
    private PostRepository postRepository;
    private SiteRepository siteRepository;
    private TermRepository termRepository;
    private MainView mainView;
    
    public MainController(PostRepository postRepository, 
                          SiteRepository siteRepository, 
                          TermRepository termRepository) {
        this.postRepository = postRepository;
        this.siteRepository = siteRepository;
        this.termRepository = termRepository;
        
        setLookAndFeel();
        setupComponents();
    }

    public void show() {
        Runnable mainViewThread = () -> mainView.setVisible(true);
        mainViewThread.run();
    }

    private void setupComponents() {
        mainView = new MainView();
        
        SitesController sitesController =
                new SitesController(siteRepository, mainView);
        PostsController postsController =
                new PostsController(postRepository, mainView);
        TermsController termsController =
                new TermsController(termRepository, mainView);
    }

    private void setLookAndFeel() {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            throw new RuntimeException();
        }
    }
}
