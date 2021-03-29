package cn.shyshetxwh.Swing.LayoutManager;

import javax.swing.*;
import java.awt.*;

public class CircleLayoutFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            CircleLayoutFrame frame = new CircleLayoutFrame();
            frame.setTitle("TextTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
