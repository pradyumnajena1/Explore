package LLD.library.management;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog implements SearchService {
  private Date creationDate;
  private int totalNumBooks;

  private Map<String, List<Book>> titles = new HashMap<>();
  private Map<String, List<Book>> authors = new HashMap<>();
  private Map<String, List<Book>> genres = new HashMap<>();
  private Map<Date, List<Book>> publicationDates = new HashMap<>();

  @Override
  public List<Book> searchByTitle(String title) {
    return titles.get(title);
  }

  @Override
  public List<Book> searchByAuthor(String author) {
    return authors.get(author);
  }

  @Override
  public List<Book> searchByGenre(String genre) {
    return genres.get(genre);
  }

  @Override
  public List<Book> searchByPublicationDate(String publicationDate) {
    return publicationDates.get(publicationDate);
  }
}
