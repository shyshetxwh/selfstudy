package cn.shyshetxwh.Swing.RadioButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RadioButtonFrame extends JFrame {
    private JLabel label;
    private JPanel buttonPanel;
    private ButtonGroup group;
    private static final int DEFAULT_SIZE=36;

    public RadioButtonFrame()
    {
        label=new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif",Font.PLAIN,DEFAULT_SIZE));
        add(label,BorderLayout.CENTER);

        group = new ButtonGroup();
        buttonPanel = new JPanel();
        addButton("Small",8);
        addButton("Medium",12);
        addButton("Large",18);
        addButton("Extra Large",36);

        Border etchedBorder = BorderFactory.createEtchedBorder();
        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Font Size");
        buttonPanel.setBorder(titledBorder);

        add(buttonPanel,BorderLayout.SOUTH);
        pack();


    }

    public void addButton(String name,int size)
    {
        boolean selected=(size==DEFAULT_SIZE);
        JRadioButton button = new JRadioButton(name, selected);
        group.add(button);
        buttonPanel.add(button);

        button.addActionListener(event->{
            label.setFont(new Font("Serif",Font.PLAIN,size));
        });
    }
}
