package cn.shyshetxwh.Swing.GridBagLayout;

import javax.swing.*;
import java.awt.*;

public class FontFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            FontFrame frame = new FontFrame();
            frame.setTitle("GridBoxLayoutTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
