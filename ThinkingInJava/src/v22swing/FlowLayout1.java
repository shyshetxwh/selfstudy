package v22swing;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * FileName: FlowLayout1
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 20:35
 */
public class FlowLayout1 extends JFrame {
    public FlowLayout1() throws HeadlessException {
        setLayout(new FlowLayout());
        for (int i = 0; i < 20; i++) {
            add(new JButton("Button "+i));
        }
    }

    public static void main(String[] args) {
        SwingConsole.run(new FlowLayout1(),300,300);
    }
}
