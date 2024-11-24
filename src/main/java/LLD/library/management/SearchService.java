package LLD.library.management;

import java.util.List;

public interface SearchService {
    // Search for books by title, author, genre, or publication date
    public List<Book> searchByTitle(String title);
    public List<Book> searchByAuthor(String author);
    public List<Book> searchByGenre(String genre);
    public List<Book> searchByPublicationDate(String publicationDate);

}
