package dao;

import dao.factories.DAOHibernateFactory;
import models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private static UserHibernateDAO userHibernateDAO;

    private UserHibernateDAO() {
    }

    public static UserHibernateDAO getInstance() {
        if (userHibernateDAO == null) {
            userHibernateDAO = new UserHibernateDAO();
        }
        return userHibernateDAO;
    }

    private Session getSession(){
      return DAOHibernateFactory.getSession();
    }

    @Override
    public List<User> getAllUser() {
        Session session = getSession();
        List<User> userList = session.createQuery("FROM User").list();
        session.close();
        return userList;
    }

    @Override
    public boolean addUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        long id = (long) session.save(user);
        transaction.commit();
        session.close();
        return id > 0;
    }

    @Override
    public boolean deleteUserByID(long id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id=:paramID");
        query.setParameter("paramID", id);
        int count = query.executeUpdate();
        transaction.commit();
        session.close();
        return count == 1;
    }

    @Override
    public User getUserByID(long id) {
        Session session = getSession();
        Query query = session.createQuery("FROM User WHERE id=:paramID");
        query.setParameter("paramID", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        boolean result = false;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        Session session = getSession();
        Query query = session.createQuery("FROM User WHERE email=:paramEmail AND password=:paramPassword ");
        query.setParameter("paramEmail", email);
        query.setParameter("paramPassword", password);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public boolean validationUser(String email, String password) {
        boolean result = false;
        Session session = getSession();
        Query query = session.createQuery("FROM User WHERE email=:paramEmail");
        query.setParameter("paramEmail", email);
        User user = (User) query.uniqueResult();
        if (user != null && user.getPassword().equals(password)) {
            result = true;
        }
        session.close();
        return result;
    }
}

