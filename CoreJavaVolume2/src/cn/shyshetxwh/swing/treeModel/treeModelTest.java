package cn.shyshetxwh.swing.treeModel;

import javax.swing.*;
import java.awt.*;

public class treeModelTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ObjectInspectorFrame frame = new ObjectInspectorFrame();
            frame.setTitle("treeModelTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
