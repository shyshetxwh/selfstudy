package cn.shyshetxwh.Swing.Slider;

import javax.swing.*;
import java.awt.*;

public class SliderFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            SliderFrame frame = new SliderFrame();
            frame.setTitle("TextTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
