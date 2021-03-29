package cn.shyshetxwh.security.classLoader;

import javax.swing.*;
import java.awt.*;

public class ClassLoaderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ClassLoaderFrame frame=new ClassLoaderFrame();
            frame.setTitle("ClassLoaderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
