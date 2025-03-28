package library.data;

import java.sql.*;
import java.text.ParseException;
import library.model.Book;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author dyl4n
 */
public class BookDb {

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
                book.setQntyAvail(rs.getInt("qnty_avail")); // adjust column name if needed
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
}
