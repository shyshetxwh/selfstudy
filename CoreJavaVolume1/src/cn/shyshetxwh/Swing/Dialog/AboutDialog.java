package cn.shyshetxwh.Swing.Dialog;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner)
    {
        super(owner,"About Dialog",true);

        add(new JLabel("<html><h1><i>Core Java</i></h1><hr>By Cay Horstmann</html>"),
                BorderLayout.CENTER);

        JButton ok = new JButton("OK");
        ok.addActionListener(event->setVisible(false));

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel,BorderLayout.SOUTH);
        pack();
    }
}
