package pl.adrianherdzina.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pl.adrianherdzina.exceptions.MovieNotFoundException;
import pl.adrianherdzina.utils.SearchUtils;

public class MainWindow {
    private VBox mainVBox;
    private HBox searchSectionHBox;
    private HBox searchResultHBox;
    private MovieDetails movieDetails;
    private boolean isResultAlreadyPrinted;

    public MainWindow() {
    }

    public VBox createMainVBox() {
        mainVBox = new VBox();
        mainVBox.setSpacing(50);
        searchSectionHBox = new HBox();
        searchResultHBox = new HBox();

        movieDetails = new MovieDetails();

        Label movieTitleLabel = new Label("Movie title: ");
        movieTitleLabel.setFont(new Font(22));
        TextField movieTextField = new TextField();
        Button searchButton = new Button("Search");


        searchSectionHBox.setAlignment(Pos.CENTER);
        searchSectionHBox.getChildren().addAll(movieTitleLabel, movieTextField, searchButton);
        mainVBox.getChildren().add(searchSectionHBox);

        searchButton.setOnAction(e -> {
            searchResultHBox.getChildren().removeAll();  //Clears HBox before creating new one
            try {
                searchButtonClicked(movieTextField.getText());
            } catch (MovieNotFoundException movieNotFoundException) {
                movieNotFoundException.printStackTrace();
            }

        });
        searchButton.setDefaultButton(true);

        return mainVBox;
    }

    private void searchButtonClicked(String textFieldContent) throws MovieNotFoundException {
        if (SearchUtils.isNotEmpty(textFieldContent)) {
            if (isResultAlreadyPrinted) {
                movieDetails.updateResultHBox(textFieldContent);
            } else {
                searchResultHBox = movieDetails.createResultHBox(textFieldContent);
                mainVBox.getChildren().add(searchResultHBox);
                isResultAlreadyPrinted = true;
            }


        } else {
            SearchUtils.showAlert("Error!",
                    null,
                    "Search text field is empty!",
                    Alert.AlertType.ERROR);
        }
    }

}
