package util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper dbHelper;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver")
                    .getDeclaredConstructor().newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").           //login
                    append("password=root").    //password
                    append("&serverTimezone=Europe/Moscow");

            Connection connection = DriverManager.getConnection(url.toString());
            if (connection != null) {
                return connection;
            } else {
                throw new NullPointerException();
            }
        } catch (SQLException |
                InstantiationException |
                IllegalAccessException |
                ClassNotFoundException |
                NoSuchMethodException |
                InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

}
