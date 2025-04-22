package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        UserDao dao = new UserDaoJDBCImpl();
        UserService service = new UserServiceImpl(dao);
        service.createUsersTable();
        log.info("---------------------");
        service.saveUser("Виктор", "Журенко", (byte)3);
        service.saveUser("Александр", "Ветров", (byte)2);
        service.saveUser("Иван", "Журенко", (byte)28);
        service.saveUser("Ольга", "Михайловская", (byte)45);
        log.info("---------------------");
        service.getAllUsers();
        log.info("---------------------");
        service.cleanUsersTable();
        log.info("---------------------");
        service.dropUsersTable();
    }
}
