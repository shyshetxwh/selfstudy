package cn.shyshetxwh.swing.tableCellRender;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableCellRenderFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    public TableCellRenderFrame()  {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        PlanetTableModel model = new PlanetTableModel();
        JTable table = new JTable(model);
        table.setRowSelectionAllowed(false);
        //设置绘制器和编辑器
        table.setDefaultRenderer(Color.class,new ColorTableCellRender());
        table.setDefaultEditor(Color.class,new ColorTableCellEditor());

        //给卫星列添加下拉框编辑器
        JComboBox<Integer> moonCombo = new JComboBox<>();
        for (int i = 0; i < 21; i++) {
            moonCombo.addItem(i);
        }
        TableColumn moonColumn = table.getColumnModel().getColumn(PlanetTableModel.MOONS_COLUMN);
        moonColumn.setCellEditor(new DefaultCellEditor(moonCombo));
        //给卫星列的列头添加绘制器，并添加图片
        moonColumn.setHeaderRenderer(table.getDefaultRenderer(ImageIcon.class));
        moonColumn.setHeaderValue(new ImageIcon(getClass().getClassLoader().getResource("Moons.gif")));

        //展示表格
        table.setRowHeight(100);
        add(new JScrollPane(table),BorderLayout.CENTER);
    }
}
