package library.data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/library");

            log("✅ JNDI Lookup succeeded: DataSource initialized.");
        } catch (NamingException e) {
            logException("❌ JNDI Lookup FAILED:", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            Connection conn = dataSource.getConnection();
            log("✅ Successfully got a DB connection.");
            return conn;
        } catch (SQLException e) {
            logException("❌ Failed to get DB connection:", e);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            logException("⚠️ Error while closing connection:", e);
        }
    }

    // Helper method to log simple messages
    private void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter("C:/temp/db-connection-debug.log", true))) {
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to log exceptions with a label
    private void logException(String label, Exception e) {
        try (PrintWriter out = new PrintWriter(new FileWriter("C:/temp/db-connection-debug.log", true))) {
            out.println(label);
            e.printStackTrace(out);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
