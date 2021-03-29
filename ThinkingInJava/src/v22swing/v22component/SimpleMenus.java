package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: SimpleMenus
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 16:02
 */
public class SimpleMenus extends JFrame {
    private JTextField t=new JTextField(15);
    private ActionListener al=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            t.setText(((JMenuItem)e.getSource()).getText());
        }
    };
    private JMenu[] menus = {
            new JMenu("Winken"), new JMenu("Blinken"),
            new JMenu("Nod")
    };
    private JMenuItem[] items = {
            new JMenuItem("Fee"), new JMenuItem("Fi"),
            new JMenuItem("Fo"),  new JMenuItem("Zip"),
            new JMenuItem("Zap"), new JMenuItem("Zot"),
            new JMenuItem("Olly"), new JMenuItem("Oxen"),
            new JMenuItem("Free")
    };

    public SimpleMenus() throws HeadlessException {
        for (int i = 0; i < items.length; i++) {
            items[i].addActionListener(al);
            menus[i % 3].add(items[i]);
        }
        JMenuBar mb = new JMenuBar();
        for (JMenu menu : menus) {
            mb.add(menu);
        }
        setJMenuBar(mb);
        setLayout(new FlowLayout());
        add(t);
    }

    public static void main(String[] args) {
        SwingConsole.run(new SimpleMenus(),200,150);
    }
}
