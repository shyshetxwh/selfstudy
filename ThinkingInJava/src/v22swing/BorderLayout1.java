package v22swing;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * FileName: BorderLayout1
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 19:55
 */
public class BorderLayout1 extends JFrame {
    public BorderLayout1() throws HeadlessException {
        add(BorderLayout.NORTH,new JButton("North"));
        add(BorderLayout.SOUTH,new JButton("South"));
        add(BorderLayout.EAST,new JButton("East"));
        add(BorderLayout.WEST,new JButton("West"));
        add(BorderLayout.CENTER,new JButton("Center"));
    }

    public static void main(String[] args) {
        SwingConsole.run(new BorderLayout1(),300,250);
    }
}
