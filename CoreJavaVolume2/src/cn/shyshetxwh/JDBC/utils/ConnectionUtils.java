package cn.shyshetxwh.JDBC.utils;

import cn.shyshetxwh.JDBC.test.TestDB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties props = new Properties();
        try(InputStream in = TestDB.class.getClassLoader().getResourceAsStream("druid.properties")){
            props.load(in);
        }
        String drivers = props.getProperty("driverClassName");
        if (drivers!=null)
        {
            Class.forName(drivers);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url,username,password);
    }
}
