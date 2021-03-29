package cn.shyshetxwh.swing.stroke;

import javax.swing.*;
import java.awt.*;

public class StrokeTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            StrokeFrame frame = new StrokeFrame();
            frame.setTitle("StrokeTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
