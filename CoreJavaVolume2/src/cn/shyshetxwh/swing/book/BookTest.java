package cn.shyshetxwh.swing.book;

import javax.swing.*;
import java.awt.*;

public class BookTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            BookFrame frame = new BookFrame();
            frame.setTitle("BookTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
