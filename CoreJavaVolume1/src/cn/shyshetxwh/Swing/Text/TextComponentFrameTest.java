package cn.shyshetxwh.Swing.Text;

import javax.swing.*;
import java.awt.*;

public class TextComponentFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            TextComponentFrame frame = new TextComponentFrame();
            frame.setTitle("TextTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
