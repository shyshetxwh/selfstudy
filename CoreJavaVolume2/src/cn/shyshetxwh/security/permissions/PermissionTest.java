package cn.shyshetxwh.security.permissions;

import javax.swing.*;
import java.awt.*;


public class PermissionTest {
    public static void main(String[] args) {
        System.setProperty("java.security.policy","PermissionTest.policy");
        System.setSecurityManager(new SecurityManager());
        EventQueue.invokeLater(() ->
        {
            PermissionTestFrame frame = new PermissionTestFrame();
            frame.setTitle("PermissionTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
