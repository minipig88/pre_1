package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserJdbcDAO implements UserDAO {
    private static UserJdbcDAO userJdbcDAO;
    private Connection connection;

    private UserJdbcDAO(Connection connection) {
        this.connection = connection;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserJdbcDAO getInstance(Connection connection) {
        if (userJdbcDAO == null) {
            userJdbcDAO = new UserJdbcDAO(connection);
        }
        return userJdbcDAO;
    }


    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_table");
            while (resultSet.next()) {
                list.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));

            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO user_table " +
                             "(firstName, secondName, email, password, role) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            result = statement.executeUpdate() == 1;
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteUserByID(long id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM user_table WHERE id = ?")) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserByID(long id) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM user_table WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                     "UPDATE user_table SET firstName = ?, secondName = ?, email = ?, password = ?, role = ?" +
                             "  WHERE id = ?")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.setLong(6, user.getId());
            result = statement.executeUpdate() == 1;
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM user_table WHERE email = ? AND password = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean validationUser(String email, String password) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                     "SELECT password FROM user_table WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getString("password").equals(password);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
