package library.model;

/**
 *
 * @author Dylan Jennings
 */
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private int qntyAvail;
    
    // Constructors
    public Book() {}

    public Book(int bookId, String title, String author, String genre, int qntyAvail) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.qntyAvail = qntyAvail;
    }

    // Getters and setters
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQntyAvail() {
        return qntyAvail;
    }
    public void setQntyAvail(int qntyAvail) {
        this.qntyAvail = qntyAvail;
    }
    
    
}
