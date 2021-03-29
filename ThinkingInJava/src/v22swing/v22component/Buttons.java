package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * FileName: Buttons
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 8:10
 */
public class Buttons extends JFrame {
    private JButton jb=new JButton("JButton");
    private BasicArrowButton up=new BasicArrowButton(BasicArrowButton.NORTH);
    private BasicArrowButton down=new BasicArrowButton(BasicArrowButton.SOUTH);
    private BasicArrowButton right=new BasicArrowButton(BasicArrowButton.EAST);
    private BasicArrowButton left=new BasicArrowButton(BasicArrowButton.WEST);

    public Buttons() throws HeadlessException {
        setLayout(new FlowLayout());
        add(jb);
        add(new JToggleButton("JToggleButton"));
        add(new JCheckBox("JCheckBox"));
        add(new JRadioButton("JRadioButton"));

        JPanel jp = new JPanel();
        jp.add(up);
        jp.add(down);
        jp.add(left);
        jp.add(right);
        add(jp);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Buttons(),350,200);
    }
}
