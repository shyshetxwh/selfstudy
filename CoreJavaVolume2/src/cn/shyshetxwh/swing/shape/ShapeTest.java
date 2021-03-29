package cn.shyshetxwh.swing.shape;

import javax.swing.*;
import java.awt.*;

public class ShapeTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ShapeFrame frame = new ShapeFrame();
            frame.setTitle("ShapeTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
