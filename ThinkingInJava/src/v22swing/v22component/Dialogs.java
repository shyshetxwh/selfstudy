package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: Dialogs
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 18:38
 */

class MyDialog extends JDialog{
    public MyDialog(JFrame parent) {
        super(parent,"My dialog",true);
        setLayout(new FlowLayout());
        add(new JLabel("Here is my dialog"));
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(ok);
        setSize(150,125);
    }
}

public class Dialogs extends JFrame {
    private JButton b1=new JButton("Dialog Box");
    private MyDialog dlg=new MyDialog(null);

    public Dialogs() throws HeadlessException {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlg.setVisible(true);
            }
        });
        add(b1);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Dialogs(),125,175);
    }
}
