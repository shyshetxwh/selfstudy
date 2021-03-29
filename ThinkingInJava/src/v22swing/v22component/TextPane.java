package v22swing.v22component;

import cn.shyshetxwh.util.Generator;
import cn.shyshetxwh.util.RandomGenerator;
import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: TextPane
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 9:30
 */
public class TextPane extends JFrame {
    private JButton b=new JButton("Add Text");
    private JTextPane tp=new JTextPane();
    private static Generator sg=new RandomGenerator.RandomString(7);

    public TextPane() throws HeadlessException {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    tp.setText(tp.getText()+sg.next()+"\n");
                }
            }
        });
        add(new JScrollPane(tp));
        add(BorderLayout.SOUTH,b);
    }

    public static void main(String[] args) {
        SwingConsole.run(new TextPane(),475,425);
    }
}
