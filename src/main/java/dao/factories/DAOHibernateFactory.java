package dao.factories;

import dao.UserDAO;
import dao.UserHibernateDAO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

public class DAOHibernateFactory extends DAOFactory {
    private static DAOHibernateFactory daoHibernateFactory;

    private DAOHibernateFactory() {
    }

    public static DAOHibernateFactory getInstance() {
        if (daoHibernateFactory == null) {
            daoHibernateFactory = new DAOHibernateFactory();
        }
        return daoHibernateFactory;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public UserDAO getUserDao() {
        return UserHibernateDAO.getInstance(createSessionFactory());
    }
}
