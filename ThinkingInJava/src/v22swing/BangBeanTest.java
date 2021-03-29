package v22swing;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

/**
 * FileName: BangBeanTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 9:51
 */
public class BangBeanTest extends JFrame {
    private JTextField txt=new JTextField(20);

    class BBL implements ActionListener{
        private int count=0;

        @Override
        public void actionPerformed(ActionEvent e) {
            txt.setText("BangBean action "+count++);
        }
    }

    public BangBeanTest() throws HeadlessException {
        BangBean bb = new BangBean();
        try {
            bb.addActionListener(new BBL());
        } catch (TooManyListenersException e) {
            txt.setText("Too many listeners");
        }
        add(bb);
        add(BorderLayout.SOUTH,txt);
    }

    public static void main(String[] args) {
        SwingConsole.run(new BangBeanTest(),400,500);
    }
}
