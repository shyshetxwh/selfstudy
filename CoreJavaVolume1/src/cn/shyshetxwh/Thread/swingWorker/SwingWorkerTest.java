package cn.shyshetxwh.Thread.swingWorker;

import javax.swing.*;
import java.awt.*;

public class SwingWorkerTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            SwingWorkerFrame frame=new SwingWorkerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("SwingWorkerTest");
            frame.setVisible(true);
        });
    }
}
