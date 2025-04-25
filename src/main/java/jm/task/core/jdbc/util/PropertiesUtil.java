package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.DbException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            PROPERTIES.load(inputStream);
            log.info("Properties успешно загружены");
        } catch (DbException | IOException e) {
            throw new DbException("Не удалось загрузить properties");
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
