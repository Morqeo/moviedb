package pl.adrianherdzina;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.adrianherdzina.gui.MainWindow;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        Scene scene = new Scene(mainWindow.createMainVBox(), 1280, 720);
        stage.setScene(scene);
        stage.setTitle("MovieDB");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}