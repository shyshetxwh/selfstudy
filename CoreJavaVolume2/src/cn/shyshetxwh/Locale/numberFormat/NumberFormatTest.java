package cn.shyshetxwh.Locale.numberFormat;

import javax.swing.*;
import java.awt.*;

public class NumberFormatTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            NumberFormatFrame frame=new NumberFormatFrame();
            frame.setTitle("NumberFormatTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
