package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: Lists
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 10:30
 */
public class Lists extends JFrame {
    private String[] flavors = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };
    private DefaultListModel lItems=new DefaultListModel();
    private JList lst=new JList(lItems);
    private JTextArea t=new JTextArea(flavors.length,20);
    private JButton b=new JButton("Add Item");
    private int count=0;
    private ActionListener bl=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (count<flavors.length){
                lItems.add(0,flavors[count++]);
            }
            else{
                b.setEnabled(false);
            }
        }
    };
    private ListSelectionListener ll=new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) return;
            t.setText("");
            for (Object item : lst.getSelectedValues()) {
                t.append(item+"\n");
            }
        }
    };

    public Lists() throws HeadlessException {
        t.setEditable(false);
        setLayout(new FlowLayout());

        Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
        lst.setBorder(brd);
        t.setBorder(brd);

        for (int i = 0; i < 4; i++) {
            lItems.addElement(flavors[count++]);
        }
        add(t);
        add(lst);
        add(b);
        lst.addListSelectionListener(ll);
        b.addActionListener(bl);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Lists(),250,375);
    }
}
