package cn.shyshetxwh.swing.table;

import javax.swing.*;
import java.awt.*;

public class TableTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            PlanetTableFrame frame = new PlanetTableFrame();
            frame.setTitle("TableTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
