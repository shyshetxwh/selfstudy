package cn.shyshetxwh.swing.tableRowColumn;

import javax.swing.*;
import java.awt.*;

public class TableRowColumnTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            PlanetTableFrame frame = new PlanetTableFrame();
            frame.setTitle("TableRowColumnTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
