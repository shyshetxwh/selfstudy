package cn.shyshetxwh.swing.imageIO;


import javax.swing.*;
import java.awt.*;

public class ImageIOTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            ImageIOFrame frame = new ImageIOFrame();
            frame.setTitle("CompositeTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
