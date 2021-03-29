package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * FileName: TabbedPanel
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 10:54
 */
public class TabbedPanel extends JFrame {
    private String[] flavors = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };
    private JTabbedPane tabs=new JTabbedPane();
    private JTextField txt=new JTextField(20);

    public TabbedPanel() throws HeadlessException {
        int i=0;
        for (String flavor : flavors) {
            tabs.addTab(flavors[i],new JButton("Tabbed pane"+i++));
        }
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt.setText("Tab selected: "+tabs.getSelectedIndex());
            }
        });
        add(BorderLayout.SOUTH,txt);
        add(tabs);
    }

    public static void main(String[] args) {
        SwingConsole.run(new TabbedPanel(),400,250);
    }
}
