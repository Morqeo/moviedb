package pl.adrianherdzina.utils;

import javafx.scene.control.Alert;

public class SearchUtils {

    private SearchUtils() {
    }

    public static boolean isNotEmpty(String string) {
        return !string.isBlank();
    }

    public static String replaceSpacesForURI(String input) {
        return input.replaceAll(" ", "+");
    }

    public static void showAlert(String title, String headerText, String alertContent, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(alertContent);
        alert.showAndWait();
    }

}
