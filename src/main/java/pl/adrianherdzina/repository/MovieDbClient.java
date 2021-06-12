package pl.adrianherdzina.repository;

import javafx.scene.control.Alert;
import pl.adrianherdzina.exceptions.MovieNotFoundException;
import pl.adrianherdzina.model.dao.MovieEntity;
import pl.adrianherdzina.model.dto.MovieData;
import pl.adrianherdzina.utils.DataUtils;
import pl.adrianherdzina.utils.SearchUtils;

import javax.persistence.*;

public class MovieDbClient {

    public MovieEntity findMovieInDb(String title) throws MovieNotFoundException {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDBPU");
        final EntityManager em = emf.createEntityManager();
        try {
            final TypedQuery<MovieEntity> movieQuery = em.createQuery(
                    "SELECT m FROM MovieEntity m WHERE m.title LIKE :title", MovieEntity.class);
            movieQuery.setParameter("title", title);
            return movieQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            OMDbClient omDbClient = new OMDbClient();
            String searchOMDBStr = SearchUtils.replaceSpacesForURI(title);
            MovieData movieData = omDbClient.downloadData(searchOMDBStr);

            if (!movieData.isResponse()) {
                SearchUtils.showAlert("Error!", null, "Movie \"" + title +
                        "\" was not found in the database and The Open Movie Database.", Alert.AlertType.ERROR);
                throw new MovieNotFoundException(title);
            } else {
                MovieEntity movieEntity = DataUtils.convertMovieDataToMovieEntity(movieData);
                addNewMovie(movieEntity);
                return movieEntity;
            }
        } finally {
            emf.close();
        }
    }

    public void addNewMovie(MovieEntity movieEntity) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDBPU");
        final EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(movieEntity);
            em.getTransaction().commit();
        } finally {
            emf.close();
        }


    }


}
