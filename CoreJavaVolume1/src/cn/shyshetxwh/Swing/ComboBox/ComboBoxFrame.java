package cn.shyshetxwh.Swing.ComboBox;

import javax.swing.*;
import java.awt.*;

public class ComboBoxFrame extends JFrame {
    private JLabel label;
    private JPanel comboPanel;
    private JComboBox<String> faceCombo;
    private static final int DEFAULT_SIZE=24;

    public ComboBoxFrame()
    {
        label = new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif",Font.PLAIN,DEFAULT_SIZE));
        add(label,BorderLayout.CENTER);

        faceCombo=new JComboBox<String>();
        faceCombo.addItem("Serif");
        faceCombo.addItem("SanSerif");
        faceCombo.addItem("Monospaced");
        faceCombo.addItem("Dialog");
        faceCombo.addItem("DialogInput");

        faceCombo.addActionListener(event->{
            String item = faceCombo.getItemAt(faceCombo.getSelectedIndex());
            label.setFont(new Font(item,Font.PLAIN,DEFAULT_SIZE));
        });

        comboPanel=new JPanel();
        comboPanel.add(faceCombo);

        add(comboPanel,BorderLayout.SOUTH);
        pack();




    }
}
