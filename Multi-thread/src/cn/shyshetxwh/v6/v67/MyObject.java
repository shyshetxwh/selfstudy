package cn.shyshetxwh.v6.v67;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * FileName: MyObject
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 17:00
 */
public class MyObject {
    public enum MyEnumSingleton {
        connectionFactory;

        private Connection connection;

        private MyEnumSingleton() {
            try {
                System.out.println("MyObject construct");
                String url = "jdbc:mysql:///travel";
                String username = "root";
                String password = "611028";
                String driverName = "com.mysql.jdbc.Driver";
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Connection getConnection() {
            return connection;
        }
    }

    public static Connection getConnection() {
        return MyEnumSingleton.connectionFactory.getConnection();
    }
}
