package LLD.moviebooking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieCatalog implements SearchService{

    private Map<String,List<Movie>> genreIndex = new HashMap<String,List<Movie>>();
    private Map<String,List<Movie>> languageIndex = new HashMap<String,List<Movie>>();
    private Map<String,List<Movie>> cityIndex = new HashMap<String,List<Movie>>();
    private Map<String,List<Movie>> titleIndex = new HashMap<String,List<Movie>>();
    private Map<String,List<Movie>> releaseDateIndex = new HashMap<String,List<Movie>>();
    @Override
    public List<Movie> findByGenre(String genre) {
        return genreIndex.get(genre);
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return titleIndex.get(title);
    }

    @Override
    public List<Movie> findByReleaseDate(String date) {
        return releaseDateIndex.get(date);
    }

    @Override
    public List<Movie> findByCity(String city) {
        return cityIndex.get(city);
    }

    @Override
    public List<Movie> findByLanguage(String language) {
        return languageIndex.get(language);
    }
}
