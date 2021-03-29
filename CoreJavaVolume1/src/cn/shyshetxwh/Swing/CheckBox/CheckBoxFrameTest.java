package cn.shyshetxwh.Swing.CheckBox;

import javax.swing.*;
import java.awt.*;

public class CheckBoxFrameTest {

        public static void main(String[] args) {
            EventQueue.invokeLater(()->{
                CheckBoxFrame frame = new CheckBoxFrame();
                frame.setTitle("TextTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            });
        }
    }

