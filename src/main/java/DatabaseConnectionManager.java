import java.io.IOException;
import java.sql.*;

public class DatabaseConnectionManager {
    private PropertyManager propertyManager = new PropertyManager();
    private final String SCHEMA = propertyManager.getProperty("schema");
    private final String LOCATION = propertyManager.getProperty("location");
    private final String TIMEZONE = propertyManager.getProperty("timezone");
    private final String DIALECT = propertyManager.getProperty("dialect");
    private final String URL = "jdbc:" + DIALECT + "://" + LOCATION + "/" + SCHEMA + "?serverTimezone=" + TIMEZONE;
    private final String USER = propertyManager.getProperty("username");
    private final String PASS = propertyManager.getProperty("password");
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public DatabaseConnectionManager() throws IOException {
    }

    public void openConnection() throws SQLException {
        con = DriverManager.getConnection(URL, USER, PASS);
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public void closeConnection() throws SQLException {
        stmt = null;
        rs = null;
        con.close();
    }

    public Connection getConnection() throws SQLException {
        return con;
    }

    public ResultSet getResultSet() throws SQLException {
        return rs;
    }

    public ResultSet select(String table) throws SQLException {
        String query = "SELECT * FROM " + table + ";";

        rs = stmt.executeQuery(query);

        return rs;
    }
}