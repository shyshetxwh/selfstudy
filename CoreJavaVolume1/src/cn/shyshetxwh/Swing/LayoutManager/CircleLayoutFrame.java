package cn.shyshetxwh.Swing.LayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CircleLayoutFrame extends JFrame {
private JPanel panel;

    public CircleLayoutFrame()
    {
        panel=new JPanel();
        panel.setLayout(new CircleLayout());
        panel.setBackground(Color.RED);

        panel.add(new JButton(new ColorAction("Red",Color.RED)));
        panel.add(new JButton(new ColorAction("Orange",Color.ORANGE)));
        panel.add(new JButton(new ColorAction("Yellow",Color.YELLOW)));
        panel.add(new JButton(new ColorAction("Green",Color.GREEN)));
        panel.add(new JButton(new ColorAction("Indigo",Color.CYAN)));
        panel.add(new JButton(new ColorAction("Blue",Color.BLUE)));
        panel.add(new JButton(new ColorAction("Fuchsia",Color.PINK)));


        add(panel);
        pack();


    }

    public class ColorAction extends AbstractAction
    {

        public  ColorAction(String name,Color c)
        {
            putValue(Action.NAME,name);
            putValue("color",c);
            putValue(Action.SHORT_DESCRIPTION,"Set panel color to "+name.toLowerCase());
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("color");
            panel.setBackground(c);
        }
    }
}
