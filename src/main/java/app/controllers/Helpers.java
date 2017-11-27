package app.controllers;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@SuppressWarnings("WeakerAccess")
public class Helpers {
    public static void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }

    public static boolean isUrlValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}
