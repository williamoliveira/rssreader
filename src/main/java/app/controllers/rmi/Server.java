package app.controllers.rmi;

import app.controllers.PostsController;
import shared.rmi.AppRemoteInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    private PostsController postsController;

    public Server(PostsController postsController) {
        this.postsController = postsController;
    }

    public void start(int port) {
        try {

            AppRemoteInterface ri = new AppRemoteInterfaceImpl(postsController);

            AppRemoteInterface stub =
                    (AppRemoteInterface) UnicastRemoteObject.exportObject(ri, port);

            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("AppRemoteInterface", stub);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        start(9191);
    }
}
