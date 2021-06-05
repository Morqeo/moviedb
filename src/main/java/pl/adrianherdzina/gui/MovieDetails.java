package pl.adrianherdzina.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pl.adrianherdzina.exceptions.MovieNotFoundException;
import pl.adrianherdzina.model.dto.MovieData;
import pl.adrianherdzina.service.DataService;
import pl.adrianherdzina.utils.SearchUtils;

public class MovieDetails {
    private HBox hBox;
    private VBox posterVBox;
    private VBox informationVBox;
    private VBox ratingVBox;

    private Image image;
    private ImageView posterImageView;

    private final Label titleTextLabel = new Label("Title: ");
    private final Label yearTextLabel = new Label("Year: ");
    private final Label releasedTextLabel = new Label("Released: ");
    private final Label runtimeTextLabel = new Label("Runtime: ");
    private final Label genreTextLabel = new Label("Genre: ");
    private final Label directorTextLabel = new Label("Director: ");
    private final Label actorsTextLabel = new Label("Actors: ");
    private final Label plotTextLabel = new Label("Plot: ");
    private final Label languageTextLabel = new Label("Language: ");
    private final Label countryTextLabel = new Label("Country: ");
    private final Label ratedTextLabel = new Label("Rated: ");
    private final Label imdbRatingText = new Label("IMDB Rating: ");

    private final Font mainFontSize = new Font(20);

    private Label titleDataLabel;
    private Label yearDataLabel;
    private Label releasedDataLabel;
    private Label runtimeDataLabel;
    private Label genreDataLabel;
    private Label directorDataLabel;
    private Label actorsDataLabel;
    private Label plotDataLabel;
    private Label languageDataLabel;
    private Label countryDataLabel;
    private Label ratedDataLabel;

    private Label imdbRatingLabel;

    private HBox titleHBox;
    private HBox yearHBox;
    private HBox releasedHBox;
    private HBox runtimeHBox;
    private HBox genreHBox;
    private HBox directorHBox;
    private VBox actorsVBox;
    private VBox plotVBox;
    private HBox languageHBox;
    private HBox countryHBox;
    private HBox ratedHBox;

    private DataService dataService;
    private MovieData movieData;


    public MovieDetails() {
        hBox = new HBox();
        posterVBox = new VBox();
        posterVBox.setMaxWidth(350);
        informationVBox = new VBox();
        informationVBox.setMaxWidth(500);
        ratingVBox = new VBox();
        ratingVBox.setMaxWidth(150);
        dataService = new DataService();
    }


    public HBox createResultHBox(String title) throws MovieNotFoundException {
        dataService.setTitle(title);
        movieData = dataService.getMovieData();

        if (!movieData.isResponse()) {
            SearchUtils.showAlert("Error!", null, "Movie \"" + title +
                    "\" was not found in the database.", Alert.AlertType.ERROR);
            throw new MovieNotFoundException(title);
        }


        initializeLabels();
        initHBoxesForData();
        setFontSizeForLabels();

        informationVBox.setPadding(new Insets(0, 50, 0, 50));

        setDataLabels();
        initPosterImage();


        posterImageView.setFitWidth(300);
        posterImageView.setFitHeight(444);

        posterVBox.getChildren().add(posterImageView);
        informationVBox.getChildren().addAll(
                titleHBox,
                yearHBox,
                releasedHBox,
                runtimeHBox,
                genreHBox,
                directorHBox,
                actorsVBox,
                plotVBox,
                languageHBox,
                countryHBox,
                ratedHBox
        );

        ratingVBox.getChildren().addAll(imdbRatingText, imdbRatingLabel);
        hBox.getChildren().addAll(posterVBox, informationVBox, ratingVBox);
        return hBox;
    }

    public void updateResultHBox(String replacedString) {
        dataService.setTitle(replacedString);
        movieData = dataService.getMovieData();
        setPosterImage();
        setDataLabels();
    }

    public void setDataLabels() {
        initPosterImage();
        titleDataLabel.setText(movieData.getTitle());
        yearDataLabel.setText(String.valueOf(movieData.getYear()));
        releasedDataLabel.setText(movieData.getReleased());
        runtimeDataLabel.setText(movieData.getRuntime());
        genreDataLabel.setText(movieData.getGenre());
        directorDataLabel.setText(movieData.getDirector());
        actorsDataLabel.setText(movieData.getActors());
        plotDataLabel.setText(movieData.getPlot());
        languageDataLabel.setText(movieData.getLanguage());
        countryDataLabel.setText(movieData.getCountry());
        ratedDataLabel.setText(movieData.getRated());
        imdbRatingLabel.setText(String.valueOf(movieData.getImdbRating()));
    }

    public void initPosterImage() {
        image = new Image(movieData.getPosterLink());
        posterImageView = new ImageView();
        posterImageView.setImage(image);
    }

    public void setPosterImage() {
        image = new Image(movieData.getPosterLink());
        posterImageView.setImage(image);
    }

    public void initializeLabels() {
        titleDataLabel = new Label();
        yearDataLabel = new Label();
        releasedDataLabel = new Label();
        runtimeDataLabel = new Label();
        genreDataLabel = new Label();
        directorDataLabel = new Label();
        actorsDataLabel = new Label();
        actorsDataLabel.setWrapText(true);
        plotDataLabel = new Label();
        plotDataLabel.setWrapText(true);
        languageDataLabel = new Label();
        countryDataLabel = new Label();
        ratedDataLabel = new Label();
        imdbRatingLabel = new Label();
    }

    public void setFontSizeForLabels() {

        titleTextLabel.setFont(mainFontSize);
        yearTextLabel.setFont(mainFontSize);
        releasedTextLabel.setFont(mainFontSize);
        runtimeTextLabel.setFont(mainFontSize);
        genreTextLabel.setFont(mainFontSize);
        directorTextLabel.setFont(mainFontSize);
        actorsTextLabel.setFont(mainFontSize);
        plotTextLabel.setFont(mainFontSize);
        languageTextLabel.setFont(mainFontSize);
        countryTextLabel.setFont(mainFontSize);
        ratedTextLabel.setFont(mainFontSize);

        titleDataLabel.setFont(mainFontSize);
        yearDataLabel.setFont(mainFontSize);
        releasedDataLabel.setFont(mainFontSize);
        runtimeDataLabel.setFont(mainFontSize);
        genreDataLabel.setFont(mainFontSize);
        directorDataLabel.setFont(mainFontSize);
        actorsDataLabel.setFont(new Font(14));
        plotDataLabel.setFont(new Font(11));
        languageDataLabel.setFont(mainFontSize);
        countryDataLabel.setFont(mainFontSize);
        ratedDataLabel.setFont(mainFontSize);
        imdbRatingLabel.setFont(new Font(36));
    }

    public void initHBoxesForData() {
        titleHBox = new HBox();
        titleHBox.getChildren().addAll(titleTextLabel, titleDataLabel);
        yearHBox = new HBox();
        yearHBox.getChildren().addAll(yearTextLabel, yearDataLabel);
        releasedHBox = new HBox();
        releasedHBox.getChildren().addAll(releasedTextLabel, releasedDataLabel);
        runtimeHBox = new HBox();
        runtimeHBox.getChildren().addAll(runtimeTextLabel, runtimeDataLabel);
        genreHBox = new HBox();
        genreHBox.getChildren().addAll(genreTextLabel, genreDataLabel);
        directorHBox = new HBox();
        directorHBox.getChildren().addAll(directorTextLabel, directorDataLabel);
        actorsVBox = new VBox();
        actorsVBox.getChildren().addAll(actorsTextLabel, actorsDataLabel);
        plotVBox = new VBox();
        plotVBox.getChildren().addAll(plotTextLabel, plotDataLabel);
        languageHBox = new HBox();
        languageHBox.getChildren().addAll(languageTextLabel, languageDataLabel);
        countryHBox = new HBox();
        countryHBox.getChildren().addAll(countryTextLabel, countryDataLabel);
        ratedHBox = new HBox();
        ratedHBox.getChildren().addAll(ratedTextLabel, ratedDataLabel);
    }


}
