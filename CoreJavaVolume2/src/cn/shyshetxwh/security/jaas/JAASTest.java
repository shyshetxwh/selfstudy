package cn.shyshetxwh.security.jaas;

import javax.swing.*;
import java.awt.*;

public class JAASTest {
    public static void main(String[] args) {
        System.setProperty("java.security.policy","CoreJavaVolume2/resource/JAASTest.policy");
        System.setProperty("java.security.auth.login.config","CoreJavaVolume2/resource/jaas1.config");
        System.setSecurityManager(new SecurityManager());

        EventQueue.invokeLater(() ->
        {
            JAASFrame frame = new JAASFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("JAASTest");
            frame.setVisible(true);
        });
    }
}
