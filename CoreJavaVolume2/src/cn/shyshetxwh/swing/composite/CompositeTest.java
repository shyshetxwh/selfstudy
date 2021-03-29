package cn.shyshetxwh.swing.composite;

import javax.swing.*;
import java.awt.*;

public class CompositeTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            CompositeFrame frame = new CompositeFrame();
            frame.setTitle("CompositeTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
