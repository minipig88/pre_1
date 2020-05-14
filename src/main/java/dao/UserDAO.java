package dao;

import models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();

    boolean addUser(User user);

    boolean deleteUserByID(long id);

    User getUserByID(long id);

    boolean updateUser(User user);

    User getUserByEmailAndPassword(String email, String password);

    boolean validationUser(String email, String password);
}
