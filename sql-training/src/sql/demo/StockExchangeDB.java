package sql.demo;
import sql.demo.repository.*;
import java.sql.*;

public class StockExchangeDB {
    // Блок объявления констант
    public static final String DB_URL = "jdbc:mysql://localhost:3306/traider_db";
//    public static final String DB_Driver = "com.mysql.jdbc.Driver";
    static final String USER = System.getenv("USER");
    static final String PASSWORD = System.getenv("PASSWORD");

    // Таблицы СУБД
    Traiders traiders;
    ShareRates shareRates;
    Shares shares;
    TraiderShareActions traiderShareActions;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Инициализация
    public StockExchangeDB() throws SQLException, ClassNotFoundException {
//        Class.forName(DB_Driver);
        // Инициализируем таблицы
        traiders = new Traiders();
        shares = new Shares();
        shareRates = new ShareRates();
        traiderShareActions = new TraiderShareActions();
    }

    // Создание всех таблиц и внешних ключей
    public void createTablesAndForeignKeys() throws SQLException {
        shares.createTable();
        shareRates.createTable();
        traiders.createTable();
        traiderShareActions.createTable();
        // Создание внешних ключей (связи между таблицами)
        shareRates.createForeignKeys();
        traiderShareActions.createForeignKeys();
    }


    public static void main(String[] args) {
        try{
            StockExchangeDB stockExchangeDB = new StockExchangeDB();
            stockExchangeDB.createTablesAndForeignKeys();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
