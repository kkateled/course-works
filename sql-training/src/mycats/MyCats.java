package mycats;
import java.sql.*;

public class MyCats {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
//    public static final String DB_Driver = "com.mysql.jdbc.Driver";
    static final String USER = System.getenv("USER");
    static final String PASSWORD = System.getenv("PASSWORD");

    public static void main(String[] args){
        try {
            Connection initConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement initStatement = initConnection.createStatement();
            String SQL = "CREATE DATABASE IF NOT EXISTS My_cats";
            initStatement.executeUpdate(SQL);

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/my_cats", USER, PASSWORD);
            Statement statement = connection.createStatement();
            SQL = "CREATE TABLE IF NOT EXISTS types " +
                    "(id INTEGER, " +
                    " type VARCHAR(100) not NULL)";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        }
    }
}