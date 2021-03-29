package cn.shyshetxwh.Locale.collation;

import javax.swing.*;
import java.awt.*;

public class CollationTest {
    public static void main(String[] args) {

        EventQueue.invokeLater(()->{

            CollationFrame frame = new CollationFrame();
            frame.setTitle("CollationTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
