package pl.adrianherdzina.utils;

import pl.adrianherdzina.model.dao.MovieEntity;
import pl.adrianherdzina.model.dto.MovieData;

public class DataUtils {

    public static MovieEntity convertMovieDataToMovieEntity(MovieData movieData) {
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setTitle(movieData.getTitle());
        movieEntity.setYear(movieData.getYear());
        movieEntity.setRated(movieData.getRated());
        movieEntity.setReleased(movieData.getReleased());
        movieEntity.setRuntime(movieData.getRuntime());
        movieEntity.setGenre(movieData.getGenre());
        movieEntity.setDirector(movieData.getDirector());
        movieEntity.setActors(movieData.getActors());
        movieEntity.setPlot(movieData.getPlot());
        movieEntity.setLanguage(movieData.getLanguage());
        movieEntity.setCountry(movieData.getCountry());
        movieEntity.setPosterLink(movieData.getPosterLink());
        movieEntity.setImdbRating(movieData.getImdbRating());

        return movieEntity;
    }
}
