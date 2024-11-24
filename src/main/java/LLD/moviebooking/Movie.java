package LLD.moviebooking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
  private String title;
  private Date releaseDate;
  private String genre;
  private String language;
  private String director;

  public Movie(String title, Date releaseDate, String genre, String language, String director) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.genre = genre;
    this.language = language;
    this.director = director;
  }

  public List<Show> getShows() {
    return new ArrayList<>();
  }
}
