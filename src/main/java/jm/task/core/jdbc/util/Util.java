package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "Liza";
    private static String password = "123456";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        if (connection != null) {
            System.out.println("Успешное подключение к базе данных");
        } else {
            System.out.println("Не удалось подключиться к базе данных");
        }
        return connection;
    }
}
