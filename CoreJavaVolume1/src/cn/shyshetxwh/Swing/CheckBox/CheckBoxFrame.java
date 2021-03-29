package cn.shyshetxwh.Swing.CheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CheckBoxFrame extends JFrame {
    private static final int FONTSIZE=24;
    private JCheckBox bold;
    private JCheckBox italic;
    private JLabel label;
    public CheckBoxFrame()
    {
        label = new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif",Font.BOLD,FONTSIZE));
        add(label,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        ActionListener listener=event->{
            int mode=0;
            if(bold.isSelected())
            {
                mode+=Font.BOLD;
            }
            if(italic.isSelected())
            {
                mode+=Font.ITALIC;
            }
            label.setFont(new Font("Serif",mode,FONTSIZE));
        };

        bold = new JCheckBox("Bold");
        bold.setSelected(true);
        bold.addActionListener(listener);
        italic = new JCheckBox("Italic");
        italic.addActionListener(listener);

        buttonPanel.add(bold);
        buttonPanel.add(italic);

        add(buttonPanel,BorderLayout.SOUTH);
        pack();


    }
}
