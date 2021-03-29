package cn.shyshetxwh.ScriptCompileAnnotate.buttons3;

import javax.swing.*;
import java.awt.*;

public class ButtonFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ButtonFrame frame = new ButtonFrame();
            frame.setTitle("ButtonFrameTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
