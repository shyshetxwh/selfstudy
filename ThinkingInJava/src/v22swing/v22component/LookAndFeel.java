package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * FileName: LookAndFeel
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 20:17
 */
public class LookAndFeel extends JFrame {
    private String[] choices =
            "Eeny Meeny Minnie Mickey Moe Larry Curly".split(" ");
    private Component[] samples = {
            new JButton("JButton"),
            new JTextField("JTextField"),
            new JLabel("JLabel"),
            new JCheckBox("JCheckBox"),
            new JRadioButton("Radio"),
            new JComboBox(choices),
            new JList(choices),
    };

    public LookAndFeel() throws HeadlessException {
        super("Look and Feel");
        setLayout(new FlowLayout());
        for (Component component : samples) {
            add(component);
        }
    }
    private static void usageError(){
        System.out.println(
                "Usage:LookAndFeel [cross|system|motif]");
        System.exit(1);
    }

    public static void main(String[] args) {
        String[] args1={"cross"};
        if(args1.length == 0) usageError();
        if(args1[0].equals("cross")) {
            try {
                UIManager.setLookAndFeel(UIManager.
                        getCrossPlatformLookAndFeelClassName());
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(args1[0].equals("system")) {
            try {
                UIManager.setLookAndFeel(UIManager.
                        getSystemLookAndFeelClassName());
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(args1[0].equals("motif")) {
            try {
                UIManager.setLookAndFeel("com.sun.java."+
                        "swing.plaf.motif.MotifLookAndFeel");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else usageError();
        // Note the look & feel must be set before
        // any components are created.
        SwingConsole.run(new LookAndFeel(),300,300);
    }
}
