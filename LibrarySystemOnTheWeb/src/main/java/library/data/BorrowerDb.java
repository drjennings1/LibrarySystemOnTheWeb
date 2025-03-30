package library.data;

import library.model.Borrower;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author dyl4n
 */
public class BorrowerDb {
    
    /**
     *
     * @param borrower
     * @return
     */
    public static int insert(Borrower borrower){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO borrowers (name, email, phone, membership_date)"
             + "VALUES (?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, borrower.getName());
            ps.setString(2, borrower.getEmail());
            ps.setString(3, borrower.getPhone());
            ps.setDate(4, borrower.getMembershipDate());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static List<Borrower> selectAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Borrower> borrowers = new ArrayList<>();

        String query = "SELECT * FROM borrowers";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Borrower borrower = new Borrower();
                borrower.setBorrowerId(rs.getInt("borrower_id"));
                borrower.setName(rs.getString("name"));
                borrower.setEmail(rs.getString("email"));
                borrower.setPhone(rs.getString("phone"));
                borrower.setMembershipDate(rs.getDate("membership_date")); // adjust column name if needed
                borrowers.add(borrower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return borrowers;
    }
}
