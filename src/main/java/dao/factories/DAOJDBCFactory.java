package dao.factories;


import dao.UserDAO;
import dao.UserJdbcDAO;
import util.DBHelper;

public class DAOJDBCFactory extends DAOFactory {
    private static DAOJDBCFactory daojdbcFactory;

    private DAOJDBCFactory() {
    }

    public static DAOJDBCFactory getInstance() {
        if (daojdbcFactory == null) {
            daojdbcFactory = new DAOJDBCFactory();
        }
        return daojdbcFactory;
    }


    @Override
    public UserDAO getUserDao() {
        return UserJdbcDAO.getInstance(DBHelper.getConnection());
    }
}
