package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: ComboBoxes
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 10:15
 */
public class ComboBoxes extends JFrame {
    private String[] description={
            "Ebullient", "Obtuse", "Recalcitrant", "Brilliant",
            "Somnescent", "Timorous", "Florid", "Putrescent"
    };
    private JTextField t=new JTextField(15);
    private JComboBox c=new JComboBox();
    private JButton b=new JButton("Add items");
    private int count=0;

    public ComboBoxes() throws HeadlessException {
        for (int i = 0; i < 4; i++) {
            c.addItem(description[count++]);
        }
        t.setEditable(false);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count<description.length)
                    c.addItem(description[count++]);
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setText("index: "+c.getSelectedIndex()+"  "+
                        ((JComboBox)e.getSource()).getSelectedItem());
            }
        });
        setLayout(new FlowLayout());
        add(t);
        add(c);
        add(b);
    }

    public static void main(String[] args) {
        SwingConsole.run(new ComboBoxes(),200,175);
    }
}
