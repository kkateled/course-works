package mycats;
import java.sql.*;

public class DatabaseManager {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/my_cats";
    static final String USER = System.getenv("USER");
    static final String PASSWORD = System.getenv("PASSWORD");

    public static Connection connection;
    public static Statement statement;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        statement = connection.createStatement();
    }

    public static void create_tables() throws SQLException {
        String SQL = "CREATE TABLE IF NOT EXISTS types " +
                "(id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                " type VARCHAR(100) not NULL)";
        statement.executeUpdate(SQL);
    }

    public static void insert_type(String type) {
        try {
            String selectSql = "SELECT * FROM types WHERE type = '" + type + "'";
            ResultSet rs = statement.executeQuery(selectSql);
            if (rs.next()) {
                System.out.println("Type " + type + " already exists in the table.");
            } else {
                String SQL = "INSERT INTO types (type) VALUES (?)";
                PreparedStatement stmt = connection.prepareStatement(SQL);
                stmt.setString(1, type);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete_type(int id) throws SQLException {
        try {
            String SQL = "DELETE FROM types WHERE id=(?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update_type(int id, String new_type) throws SQLException {
        try {
            String SQL = "UPDATE types SET type=(?) WHERE id=(?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setString(1, new_type);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void get_type(int id) throws SQLException {
        try {
            String SQL = "SELECT type FROM types WHERE id=(?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                String  type = resultSet.getString(1);
                System.out.println(type);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void get_type_where(String where) throws SQLException {
        try {
            String SQL = "SELECT type FROM types WHERE " + where;
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                String  type = resultSet.getString(1);
                System.out.println(type);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void get_all_types() throws SQLException {
        try {
            String SQL = "SELECT type FROM types";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                String  type = resultSet.getString(1);
                System.out.println(type);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
