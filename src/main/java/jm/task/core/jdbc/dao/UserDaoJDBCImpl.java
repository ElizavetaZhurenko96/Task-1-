package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            log.info("Результат выполнения: {}", statement.execute(SqlQueries.createUsersTable));
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            log.info("Результат выполнения: {}", statement.execute(SqlQueries.dropUsersTable));
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.saveUser)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                log.info("User с именем {} добавлен в базу данных c id {}", name, id);
            }

        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQueries.removeUserById)) {
            statement.setLong(1, id);
            int countUsersDeleted = statement.executeUpdate();
            if (countUsersDeleted > 0) {
                log.info("Пользователь с id: {} был удален", id);
            } else {
                log.info("Пользователь с id: {} не найден", id);
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQueries.getAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }
            for (User user : usersList) {
                log.info(user.toString());
            }
            return usersList;
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SqlQueries.cleanUsersTable);
            log.info("Таблица очищена");
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        // можно использовать DELETE FROM users - удаляет построчно, можно задать условие
    }
}
