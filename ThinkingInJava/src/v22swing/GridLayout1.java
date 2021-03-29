package v22swing;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * FileName: GridLayout1
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 20:38
 */
public class GridLayout1 extends JFrame {
    public GridLayout1() throws HeadlessException {
        setLayout(new GridLayout(7,3));
        for (int i = 0; i < 20; i++) {
            add(new JButton("Button "+i));
        }
    }

    public static void main(String[] args) {
        SwingConsole.run(new GridLayout1(),300,300);
    }
}
