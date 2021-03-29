package cn.shyshetxwh.Swing.DataExchange;

import javax.swing.*;
import java.awt.*;

public class DataExchangeFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            DataExchangeFrame frame = new DataExchangeFrame();
            frame.setTitle("TextTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
