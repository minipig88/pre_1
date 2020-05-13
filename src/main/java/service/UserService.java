package service;


import dao.UserJdbcDAO;
import models.User;

import java.util.List;

public class UserService {
    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserJdbcDAO getUserDAO() {
        return UserJdbcDAO.getInstance();
    }

    public List<User> getAllUser() {
        return getUserDAO().getAllUser();
    }

    public boolean addUser(User user) {
        return getUserDAO().addUser(user);
    }

    public boolean deleteUserByID(long id) {
        return getUserDAO().deleteUserByID(id);
    }

    public User getUserByID(long id) {
        return getUserDAO().getUserByID(id);
    }

    public boolean updateUser(User user) {
        return getUserDAO().updateUser(user);
    }

    public boolean validationUser(String email, String password) {
        return getUserDAO().validationUser(email, password);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return getUserDAO().getUserByEmailAndPassword(email, password);
    }
}
