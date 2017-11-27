package app.controllers;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;

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

    public static boolean isTimeValid(String time) {
        Pattern pattern = Pattern.compile("([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]");

        return pattern.matcher(time).matches();
    }

}
