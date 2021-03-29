package cn.shyshetxwh.v6.v66;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum MyObject {
    connectionFactory;

    private Connection connection;

    private MyObject() {
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
