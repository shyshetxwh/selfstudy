package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: RadioButtons
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 9:51
 */
public class RadioButtons extends JFrame {
    private JTextField t=new JTextField(15);
    private ButtonGroup g=new ButtonGroup();
    private JRadioButton
        rb1=new JRadioButton("one",false),
        rb2=new JRadioButton("two",false),
        rb3=new JRadioButton("three",false);
    private ActionListener al=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            t.setText("Radio button "+((JRadioButton)e.getSource()).getText());
        }
    };

    public RadioButtons() throws HeadlessException {
        rb1.addActionListener(al);
        rb2.addActionListener(al);
        rb3.addActionListener(al);
        g.add(rb1);
        g.add(rb2);
        g.add(rb3);
        t.setEditable(false);
        setLayout(new FlowLayout());
        add(t);
        add(rb1);
        add(rb2);
        add(rb3);
    }

    public static void main(String[] args) {
        SwingConsole.run(new RadioButtons(),200,125);
    }
}
