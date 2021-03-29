package cn.shyshetxwh.Swing.Dialog;

import javax.swing.*;
import java.awt.*;

public class DialogFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            DialogFrame frame = new DialogFrame();
            frame.setTitle("Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
