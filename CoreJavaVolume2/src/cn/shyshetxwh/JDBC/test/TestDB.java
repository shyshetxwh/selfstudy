package cn.shyshetxwh.JDBC.test;

import cn.shyshetxwh.JDBC.utils.ConnectionUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class TestDB {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try {
            runTest();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    private static void runTest() throws SQLException, IOException, ClassNotFoundException {
        try(Connection con= ConnectionUtils.getConnection();
            Statement stat=con.createStatement()){
            stat.executeUpdate("create table Greetings(Message CHAR(20))");
            stat.executeUpdate("insert into Greetings VALUES('Hello World!')");

            try (ResultSet rs=stat.executeQuery("SELECT * from Greetings")){
                if (rs.next())
                {
                    System.out.println(rs.getString(1));
                }
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }
    }


}
