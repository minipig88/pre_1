package dao.factories;


import dao.UserDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DAOFactory {
    private static String daoType;
    private static DAOFactory DAOFactory;
    public abstract UserDAO getUserDao();

    private static String getDaoType() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = DAOFactory.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("daoType");
    }

    public static DAOFactory getDAOFactory() {
        daoType = getDaoType();
        if (daoType.equals("dao.UserHibernateDAO")) {
            DAOFactory = DAOHibernateFactory.getInstance();
        } else if (daoType.equals("dao.UserJdbcDAO")) {
            DAOFactory = DAOJDBCFactory.getInstance();
        }
        return DAOFactory;
    }

}
