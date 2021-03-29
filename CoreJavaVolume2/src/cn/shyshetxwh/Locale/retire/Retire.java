package cn.shyshetxwh.Locale.retire;

import javax.swing.*;
import java.awt.*;

public class Retire {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            RetireFrame frame = new RetireFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
