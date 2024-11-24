package LLD.moviebooking;

import java.util.ArrayList;
import java.util.List;

public interface SearchService {

    public List<Movie> findByGenre(String genre) ;
    public List<Movie> findByTitle(String title) ;
    public List<Movie> findByReleaseDate(String date) ;
    public List<Movie> findByCity(String city) ;
    public List<Movie> findByLanguage(String language) ;
}
