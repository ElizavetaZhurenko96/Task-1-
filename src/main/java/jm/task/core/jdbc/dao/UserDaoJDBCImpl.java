package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR,
                    lastName VARCHAR,
                    age SMALLINT
                )
                """;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println(statement.execute(sql));

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println(statement.execute(sql));
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) values (?, ?, ?) RETURNING id";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                System.out.println("User с именем " + name + " добавлен в базу данных c id " + id);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int countUsersDeleted = statement.executeUpdate();
            if (countUsersDeleted > 0) {
                System.out.println("Пользователь с id: " + id + " был удален");
            } else {
                System.out.println("Пользователь с id: " + id + " не найден");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }
            for (User user : usersList) {
                System.out.println(user.toString());
            }
            return usersList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // можно использовать DELETE FROM users - удаляет построчно, можно задать условие
    }
}
