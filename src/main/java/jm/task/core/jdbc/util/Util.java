package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.DbException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Util {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
            log.info("Соединение успешно установлено");
            return connection;
        } catch (SQLException ex) {
            log.error("Не удалось установить соединение с СУБД");
            throw new DbException(ex.getMessage());
        }
    }
}
