package app.controllers.rmi;

import app.controllers.PostsController;
import shared.rmi.AppRemoteInterface;

import java.rmi.RemoteException;

public class AppRemoteInterfaceImpl implements AppRemoteInterface {

    private PostsController postsController;

    public AppRemoteInterfaceImpl(PostsController postsController) throws RemoteException {
        this.postsController = postsController;
    }

    public void refreshPostsTable() throws RemoteException {
        postsController.refreshPostsTable();
    }
}
