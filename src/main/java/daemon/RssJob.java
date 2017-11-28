package daemon;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.models.post.PostRepository;
import shared.models.site.SiteRepository;
import shared.models.term.TermRepository;
import shared.repository.RepositoryFactory;
import shared.rmi.AppRemoteInterface;
import shared.services.rss.FetchAndSave;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RssJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(RssJob.class);

    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        PostRepository postRepository
                = RepositoryFactory.getFactory().getPostRepository();
        SiteRepository siteRepository
                = RepositoryFactory.getFactory().getSiteRepository();
        TermRepository termRepository
                = RepositoryFactory.getFactory().getTermRepository();
        FetchAndSave fetchAndSave = new FetchAndSave(
                postRepository,
                siteRepository,
                termRepository
        );

        fetchAndSave.fetchAndSave();
        refreshTableIfAppOpen();
    }

    private void refreshTableIfAppOpen() {
        refreshTableIfAppOpen(9191);
    }

    private void refreshTableIfAppOpen(int port) {
        try {
            AppRemoteInterface appRemoteInterface =
                    (AppRemoteInterface) Naming.lookup("rmi://127.0.0.1:"+ port +"/AppRemoteInterface");

            appRemoteInterface.refreshPostsTable();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
