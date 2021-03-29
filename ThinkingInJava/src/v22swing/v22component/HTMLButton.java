package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: HTMLButton
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 19:56
 */
public class HTMLButton extends JFrame {
    private JButton b=new JButton("<html><b><font size=+2>" +
            "<center>Hello!<br><i>Press me now!");

    public HTMLButton() throws HeadlessException {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(new JLabel("<html>" +
                        "<i><font size=+4>Kapow!"));
                validate();
            }
        });
        setLayout(new FlowLayout());
        add(b);
    }

    public static void main(String[] args) {
        SwingConsole.run(new HTMLButton(),200,500);
    }
}
