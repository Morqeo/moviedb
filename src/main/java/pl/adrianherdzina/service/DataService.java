package pl.adrianherdzina.service;

import pl.adrianherdzina.model.dto.MovieData;
import pl.adrianherdzina.repository.OMDbClient;

public class DataService {

    private OMDbClient omDbClient;
    private String title;

    public DataService() {
        omDbClient = new OMDbClient();
    }

    public DataService(String title) {
        this();
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieData getMovieData() {
        return omDbClient.downloadData(title);
    }

}
