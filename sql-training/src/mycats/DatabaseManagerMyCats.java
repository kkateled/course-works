package mycats;
import java.sql.*;

public class DatabaseManagerMyCats {
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
        String SQLMyCats = "CREATE TABLE IF NOT EXISTS types " +
                "(id INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE, " +
                " type VARCHAR(100) not NULL)";
        statement.executeUpdate(SQLMyCats);
        String SQLCats = "CREATE TABLE IF NOT EXISTS cats " +
                "(id INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE, " +
                " name VARCHAR(20) not NULL," +
                " type_id INTEGER not NULL, " +
                " age INTEGER not NULL, " +
                " weight DOUBLE," +
                " FOREIGN KEY (type_id) REFERENCES types(id) ON DELETE CASCADE)";
        statement.executeUpdate(SQLCats);
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

    public static void insert_cat(String name, String type, int age, Double weight)  throws SQLException {
        int type_id = 0;
        try {
            String selectSql = "SELECT * FROM types WHERE type = '" + type + "'";
            ResultSet rs = statement.executeQuery(selectSql);
            if (rs.next()) {
                // Type already exists in the table.
                type_id = rs.getInt("id");
            } else {
                // Type doesn't exist in the table.
                String SQL = "INSERT INTO types (type) VALUES (?)";
                PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, type);
                stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    type_id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to get new type id.");
                }
            }
            String SQL = "INSERT INTO cats (name, type_id, age, weight) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setString(1, name);
            stmt.setInt(2, type_id);
            stmt.setInt(3, age);
            stmt.setDouble(4, weight);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete_cat(int id) throws SQLException {
        try {
            String SQL = "DELETE FROM cats WHERE id=(?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete_cat(String where)  throws SQLException {
        try {
            String SQL = "DELETE FROM cats WHERE " + where;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update_cat(int id, String set) throws SQLException {
        try {
            String SQL = "UPDATE cats SET " + set + " WHERE id=(?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update_cat(String set, String where) throws SQLException {
        try {
            String SQL = "UPDATE cats SET " + set + " WHERE " + where;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void get_cat(int id) throws SQLException {
        try {
            String SQL = "SELECT name FROM cats WHERE id=(?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                String  name = resultSet.getString(1);
                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void get_cat_where(String where) throws SQLException {
        try {
            String SQL = "SELECT name FROM cats WHERE " + where;
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                String  name = resultSet.getString(1);
                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void get_all_cats() throws SQLException {
        try {
            String SQL = "SELECT name FROM cats";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                String  name = resultSet.getString(1);
                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
