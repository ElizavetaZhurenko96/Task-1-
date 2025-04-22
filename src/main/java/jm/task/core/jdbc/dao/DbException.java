package jm.task.core.jdbc.dao;

public class DbException extends RuntimeException {

    public DbException(String message) {
        super(message);
    }

    public DbException() {
        super("Не удалось подключиться к базе данных");
    }
}
