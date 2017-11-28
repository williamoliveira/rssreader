package app.controllers;

import app.views.MainView;
import shared.entities.post.PostRepository;
import shared.entities.schedule.ScheduleRepository;
import shared.entities.site.SiteRepository;
import shared.entities.term.TermRepository;

import javax.swing.*;

public class MainController {
    private PostRepository postRepository;
    private SiteRepository siteRepository;
    private TermRepository termRepository;
    private ScheduleRepository scheduleRepository;
    private MainView mainView;
    
    public MainController(PostRepository postRepository, 
                          SiteRepository siteRepository, 
                          TermRepository termRepository, 
                          ScheduleRepository scheduleRepository) {
        this.postRepository = postRepository;
        this.siteRepository = siteRepository;
        this.termRepository = termRepository;
        this.scheduleRepository = scheduleRepository;
        
        setLookAndFeel();
        setupComponents();
    }

    public void show() {
        Runnable mainViewThread = () -> mainView.setVisible(true);
        mainViewThread.run();
    }

    private void setupComponents() {
        mainView = new MainView();
        
        new SitesController(siteRepository, mainView);
        new PostsController(postRepository, siteRepository, termRepository, mainView);
        new TermsController(termRepository, mainView);
        new SchedulesController(scheduleRepository, mainView);
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
