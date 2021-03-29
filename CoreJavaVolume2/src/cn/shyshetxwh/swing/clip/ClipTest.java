package cn.shyshetxwh.swing.clip;

import javax.swing.*;
import java.awt.*;

public class ClipTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ClipFrame frame = new ClipFrame();
            frame.setTitle("ClipTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
