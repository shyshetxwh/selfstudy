package cn.shyshetxwh.Swing.Mouse;

import javax.swing.*;
import java.awt.*;

public class MouseTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            MouseFrame frame = new MouseFrame();
            frame.setTitle("MouseFrameTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
