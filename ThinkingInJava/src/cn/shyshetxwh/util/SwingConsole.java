package cn.shyshetxwh.util;

import javax.swing.*;
import java.awt.*;

/**
 * FileName: SwingConsole
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 16:08
 */
public class SwingConsole {
    public static void run(final JFrame frame,final int width,final int height){
        EventQueue.invokeLater(()->{
            frame.setTitle(frame.getClass().getSimpleName());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width,height);
            frame.setVisible(true);
        });
    }
}
