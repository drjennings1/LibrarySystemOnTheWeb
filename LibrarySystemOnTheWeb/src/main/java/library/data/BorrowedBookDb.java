package library.data;

import java.sql.*;
import library.model.Book;
import java.util.List;
import java.util.ArrayList;
import library.model.BorrowedBook;

/**
 *
 * @author dyl4n
 */
public class BorrowedBookDb {
    
    public static int insert(int borrowerId, int bookId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO borrowed_books (book_id, borrower_id, borrow_date, return_date, returned)"
             + "VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), FALSE)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.setInt(2, borrowerId);
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    // changes the "returned" boolean to true for the respective borrowerId + bookId
    public static int markReturned(int borrowerId, int bookId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE borrowed_books "
                + "SET returned = True "
                + "WHERE book_id = ? AND borrower_id = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.setInt(2, borrowerId);
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<BorrowedBook> selectCurrentCheckouts(int borrowerId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<BorrowedBook> borrowedBooks = new ArrayList<>();

        String query = "SELECT bb.id, bb.book_id, bb.borrow_date, bb.return_date, bb.returned, " +
                       "b.title, b.author, b.genre, b.qnty_avail " +
                       "FROM borrowed_books bb " +
                       "JOIN books b ON bb.book_id = b.book_id " +
                       "WHERE bb.borrower_id = ? AND bb.returned = false";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, borrowerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setQntyAvail(rs.getInt("qnty_avail"));

                BorrowedBook bb = new BorrowedBook();
                bb.setBorrowId(rs.getInt("id"));
                bb.setBorrowerId(borrowerId);
                bb.setBookId(rs.getInt("book_id"));
                bb.setBorrowDate(rs.getString("borrow_date"));
                bb.setReturnDate(rs.getString("return_date"));
                bb.setReturned(rs.getBoolean("returned"));
                bb.setBook(book);

                borrowedBooks.add(bb);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return borrowedBooks;
    }
    
    
}
