package jm.task.core.jdbc.dao;

public class SqlQueries {


    private SqlQueries(){}


    public static final String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR,
                    lastName VARCHAR,
                    age SMALLINT
                )
                """;

    public static final String dropUsersTable = "DROP TABLE IF EXISTS users";

    public static final String saveUser = "INSERT INTO users (name, lastName, age) values (?, ?, ?) RETURNING id";

    public static final String removeUserById = "DELETE FROM users WHERE id = ?";

    public static final String getAllUsers = "SELECT * FROM users";

    public static final String cleanUsersTable = "TRUNCATE TABLE users";
}
