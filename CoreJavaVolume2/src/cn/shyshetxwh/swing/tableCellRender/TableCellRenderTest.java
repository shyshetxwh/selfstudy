package cn.shyshetxwh.swing.tableCellRender;

import javax.swing.*;
import java.awt.*;

public class TableCellRenderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            TableCellRenderFrame frame = new TableCellRenderFrame();
            frame.setTitle("TableCellRenderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
