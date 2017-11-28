package app.controllers;

import app.controllers.rmi.Server;
import app.views.MainView;
import shared.models.post.PostRepository;
import shared.models.schedule.ScheduleRepository;
import shared.models.site.SiteRepository;
import shared.models.term.TermRepository;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        setupComponents();
    }

    public void show() {
        Runnable mainViewThread = () -> mainView.setVisible(true);
        mainViewThread.run();
    }

    private void setupComponents() {
        setLookAndFeel();

        mainView = new MainView();

        mainView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        new SitesController(siteRepository, mainView);
        new TermsController(termRepository, mainView);
        new SchedulesController(scheduleRepository, mainView);
        PostsController postsController = new PostsController(
                postRepository,
                siteRepository,
                termRepository,
                mainView
        );

        Server rmiServer = new Server(postsController);
        rmiServer.start();
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
