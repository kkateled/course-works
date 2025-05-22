package mycats;

import java.sql.*;

public class MyCats {
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

    public static void insert_type(String type){
        try {
            String SQL = "INSERT INTO types (type) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setString(1, type);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Recording '"+type+"' already exists");
        }
    }
    public static void main(String[] args) {
        try{
            connect();
            create_tables();
            insert_type("Абиссинская кошка");
            insert_type("Австралийский мист");
            insert_type("Американская жесткошерстная");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error SQL !");
        }
    }
}