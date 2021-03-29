package cn.shyshetxwh.Swing.FileChooser;

import javax.swing.*;
import java.awt.*;

public class FileChooserFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            FileChooserFrame frame = new FileChooserFrame();
            frame.setTitle("TextTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
