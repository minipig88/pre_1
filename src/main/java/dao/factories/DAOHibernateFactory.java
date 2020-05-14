package dao.factories;

import dao.UserDAO;
import dao.UserHibernateDAO;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DAOHibernateFactory extends DAOFactory {
    private static DAOHibernateFactory daoHibernateFactory;
    private static SessionFactory sessionFactory;

    private DAOHibernateFactory() {
        sessionFactory = createSessionFactory();
    }

    public static DAOHibernateFactory getInstance() {
        if (daoHibernateFactory == null) {
            daoHibernateFactory = new DAOHibernateFactory();
        }
        return daoHibernateFactory;
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.connection.serverTimezone", "Europe/Moscow");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        return configuration;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Session getSession(){
      return sessionFactory.openSession();
    }

    @Override
    public UserDAO getUserDao() {
        return UserHibernateDAO.getInstance();
    }
}
