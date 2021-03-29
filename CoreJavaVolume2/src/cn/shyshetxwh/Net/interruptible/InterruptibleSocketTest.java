package cn.shyshetxwh.Net.interruptible;

import javax.swing.*;
import java.awt.*;

public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            InterruptibleSocketFrame frame = new InterruptibleSocketFrame();
            frame.setTitle("InterruptibleSocketTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }
}
