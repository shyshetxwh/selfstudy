package cn.shyshetxwh.JDBC.view;

import javax.swing.*;
import java.awt.*;

public class ViewDB {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ViewDBFrame frame=new ViewDBFrame();
            frame.setTitle("ViewDB");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        });
    }
}
