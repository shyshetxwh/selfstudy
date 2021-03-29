package cn.shyshetxwh.Swing.RadioButton;

import javax.swing.*;
import java.awt.*;

public class RadioButtonFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            RadioButtonFrame frame = new RadioButtonFrame();
            frame.setTitle("TextTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
