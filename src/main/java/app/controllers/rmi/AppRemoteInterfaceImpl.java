package app.controllers.rmi;

import app.controllers.PostsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.rmi.AppRemoteInterface;

import java.rmi.RemoteException;

public class AppRemoteInterfaceImpl implements AppRemoteInterface {

    private static Logger logger = LoggerFactory.getLogger(AppRemoteInterfaceImpl.class);

    private PostsController postsController;

    public AppRemoteInterfaceImpl(PostsController postsController) throws RemoteException {
        this.postsController = postsController;
    }

    public void refreshPostsTable() throws RemoteException {
        postsController.refreshPostsTable();
        logger.info("Called refreshPostsTable from RMI");
    }
}
