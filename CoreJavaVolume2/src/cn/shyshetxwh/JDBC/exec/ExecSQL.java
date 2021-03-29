package cn.shyshetxwh.JDBC.exec;

import cn.shyshetxwh.JDBC.test.TestDB;
import cn.shyshetxwh.JDBC.utils.ConnectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class ExecSQL {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String>list=new ArrayList<>();
        list.add("Authors.sql");
        list.add("Books.sql");
        list.add("BooksAuthors.sql");
        list.add("Publishers.sql");
        for (String sql : list) {
            System.out.println(sql);
            try(InputStream is = ExecSQL.class.getClassLoader().getResourceAsStream(sql);
                Scanner in=new Scanner(is,"UTF-8"))
            {
                try(Connection conn= ConnectionUtils.getConnection();
                    Statement stat=conn.createStatement()){
                    while(true)
                    {
                        if (!in.hasNextLine())break;
                        String line = in.nextLine().trim();
                        if (line.equalsIgnoreCase("exit"))return;
                        //去掉sql语句后面的分号
                        if (line.endsWith(";"))line=line.substring(0,line.length()-1);
                        boolean isResult = stat.execute(line);
                        if (isResult)
                        {
                            try (ResultSet rs=stat.getResultSet()){
                                showResultSet(rs);
                            }
                        }
                        else
                        {
                            int updateCount = stat.getUpdateCount();
                            System.out.println(updateCount+" rows updated");
                        }
                    }
                } catch (SQLException e) {
                    for (Throwable t : e) {
                        t.printStackTrace();
                    }
                }
            }
        }


    }

    private static void showResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <=columnCount; i++) {
            if (i>1) System.out.print(", ");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();
        while(rs.next())
        {
            for (int i = 1; i <=columnCount; i++) {
                if (i>1) System.out.print(", ");
                System.out.print(rs.getString(i));
            }
        }
        System.out.println();
    }



}
