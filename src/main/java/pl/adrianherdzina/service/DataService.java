package pl.adrianherdzina.service;

import pl.adrianherdzina.exceptions.MovieNotFoundException;
import pl.adrianherdzina.model.dao.MovieEntity;
import pl.adrianherdzina.repository.MovieDbClient;

public class DataService {

    private MovieDbClient movieDbClient;
    private String title;

    public DataService() {
        movieDbClient = new MovieDbClient();
    }

    public DataService(String title) {
        this();
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieEntity getMovieData() {

        MovieEntity movieEntity = null;
        try {
            movieEntity = movieDbClient.findMovieInDb(title);
        } catch (MovieNotFoundException e) {
            e.printStackTrace();
        }
        return movieEntity;
    }

}
