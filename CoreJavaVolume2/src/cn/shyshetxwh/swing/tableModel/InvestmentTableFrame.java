package cn.shyshetxwh.swing.tableModel;

import javax.swing.*;
import java.awt.*;

public class InvestmentTableFrame extends JFrame {
    public InvestmentTableFrame()  {
        InvestmentTableModel model = new InvestmentTableModel(30, 5, 10);
        JTable table = new JTable(model);
        add(new JScrollPane(table));
        pack();
    }
}
