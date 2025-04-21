package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        System.out.println("---------------------");
        userDaoJDBC.saveUser("Виктор", "Журенко", (byte)3);
        userDaoJDBC.saveUser("Александр", "Ветров", (byte)2);
        userDaoJDBC.saveUser("Иван", "Журенко", (byte)28);
        userDaoJDBC.saveUser("Ольга", "Михайловская", (byte)45);
        System.out.println("---------------------");
        userDaoJDBC.getAllUsers();
        System.out.println("---------------------");
        userDaoJDBC.cleanUsersTable();
        System.out.println("---------------------");
        userDaoJDBC.dropUsersTable();
    }
}
