package cn.shyshetxwh.swing.composite;

import javax.swing.*;
import java.awt.*;

public class CompositeFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    private CompositeComponent canvas;
    private JComboBox<Rule> ruleCombo;
    private JSlider slider;
    private JTextField explanation;

    public CompositeFrame()  {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        canvas=new CompositeComponent();
        add(canvas,BorderLayout.CENTER);

        ruleCombo=new JComboBox<Rule>(new Rule[]{
                new Rule("CLEAR", "  ", "  "),
                new Rule("SRC", " S", " S"), new Rule("DST", "  ", "DD"),
                new Rule("SRC_OVER", " S", "DS"), new Rule("DST_OVER", " S", "DD"),
                new Rule("SRC_IN", "  ", " S"), new Rule("SRC_OUT", " S", "  "),
                new Rule("DST_IN", "  ", " D"), new Rule("DST_OUT", "  ", "D "),
                new Rule("SRC_ATOP", "  ", "DS"), new Rule("DST_ATOP", " S", " D"),
                new Rule("XOR", " S", "D "),
        });

        ruleCombo.addActionListener(event->{
            Rule r = (Rule) ruleCombo.getSelectedItem();
            canvas.setRule(r.getValue());
            explanation.setText(r.getExplanation());
        });

        slider=new JSlider(0,100,75);
        slider.addChangeListener(event->{
            canvas.setAlpha(slider.getValue());
        });

        JPanel panel = new JPanel();
        panel.add(ruleCombo);
        panel.add(new JLabel("Alpha"));
        panel.add(slider);
        add(panel,BorderLayout.NORTH);

        explanation=new JTextField();
        add(explanation,BorderLayout.SOUTH);

        canvas.setAlpha(slider.getValue());
        Rule r = (Rule) ruleCombo.getSelectedItem();
        canvas.setRule(r.getValue());
        explanation.setText(r.getExplanation());

    }
}
