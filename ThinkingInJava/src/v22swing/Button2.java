package v22swing;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: Button2
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 17:02
 */
public class Button2 extends JFrame {
    private JButton b1=new JButton("Button1");
    private JButton b2=new JButton("Button2");
    private JTextField txt=new JTextField(10);
    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton) e.getSource()).getText();
            txt.setText(name);
        }
    }
    private ButtonListener bl=new ButtonListener();

    public Button2() throws HeadlessException {
        b1.addActionListener(bl);
        b2.addActionListener(bl);
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(txt);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Button2(),200,150);
    }
}
