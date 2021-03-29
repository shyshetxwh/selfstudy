package cn.shyshetxwh.Swing.ComboBox;

import javax.swing.*;
import java.awt.*;

public class ComboBoxFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ComboBoxFrame frame = new ComboBoxFrame();
            frame.setTitle("TextTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
