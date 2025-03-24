package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void createUsersTable() {
        String createUsersTableQuery = "CREATE TABLE IF NOT EXISTS user(Id INT AUTO_INCREMENT PRIMARY KEY, name TEXT, lastName TEXT, age TINYINT)";
        try {
            connection.createStatement().executeUpdate(createUsersTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        String dropUsersTableQuery = "DROP TABLE IF EXISTS user";
        try {
            connection.createStatement().executeUpdate(dropUsersTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserQuery = "INSERT into user(name, lastName, age) values ('" + name + "','" + lastName + "'," + age + ")";
        try {
            connection.createStatement().executeUpdate(saveUserQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        String getAllUsersQuery = "SELECT * FROM user";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(getAllUsersQuery);
            List<User> users = new ArrayList<User>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user.toString());
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void cleanUsersTable() {
        String cleanUsersTableQuery = "DELETE FROM user";
        try {
            connection.createStatement().executeUpdate(cleanUsersTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
