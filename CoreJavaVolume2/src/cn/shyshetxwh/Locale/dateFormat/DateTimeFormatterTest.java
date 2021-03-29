package cn.shyshetxwh.Locale.dateFormat;

import javax.swing.*;
import java.awt.*;

public class DateTimeFormatterTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            DateTimeFormatterFrame frame=new DateTimeFormatterFrame();
            frame.setTitle("DateTimeFormatterTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
