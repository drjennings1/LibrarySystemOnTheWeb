package library.data;

import java.sql.*;
import library.model.Book;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author dyl4n
 */
public class BookDb {
    
    public static void reduceQnty(int bookId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE books "
                + "SET qnty_avail = qnty_avail - 1 "
                + "WHERE book_id = ? AND qnty_avail > 0";
                
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void increaseQnty(int bookId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE books "
                + "SET qnty_avail = qnty_avail + 1 "
                + "WHERE book_id = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void changeQnty(int bookId, int qnty_avail){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE books "
                + "SET qnty_avail = ? "
                + "WHERE book_id = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, qnty_avail);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int insert(Book book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO books (title, author, genre, qnty_avail)"
             + "VALUES (?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setInt(4, book.getQntyAvail());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int remove(Book book){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM books "
                + "WHERE book_id = ? AND title = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getTitle());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<Book> selectAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM books";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setQntyAvail(rs.getInt("qnty_avail"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return books;
    }
    
    public static Book selectBookById(int bookId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Book book = null;
        String query = "SELECT * FROM books WHERE book_id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            if (rs.next()) {
                book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setQntyAvail(rs.getInt("qnty_avail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return book;
    }

    
}
