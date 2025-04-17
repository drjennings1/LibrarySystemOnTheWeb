package library.model;

/**
 *
 * @author Dylan Jennings
 */
public class BorrowedBook {
    private int borrowId;
    private int borrowerId;
    private int bookId;
    private String borrowDate;
    private String returnDate;
    private boolean returned;
    private Book book;

    // Constructors
    public BorrowedBook() {}

    public BorrowedBook(int borrowId, int borrowerId, int bookId, String borrowDate, String returnDate) {
        this.borrowId = borrowId;
        this.borrowerId = borrowerId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Setters and getters
    public int getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    
    public boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
    
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    
}