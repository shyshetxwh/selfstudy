package cn.shyshetxwh.Swing.Slider;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class SliderFrame extends JFrame {
    private JTextField textField;
    private JPanel slidePanel;
    private ChangeListener listener;

    public SliderFrame()
    {
        slidePanel=new JPanel();
        slidePanel.setLayout(new GridBagLayout());

        listener=event->{
            JSlider source = (JSlider) event.getSource();
            textField.setText(""+source.getValue());
        };

        JSlider slider = new JSlider();
        addSlide(slider,"Plain");

        slider=new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlide(slider,"Ticks");

        slider=new JSlider();
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlide(slider,"Snap to Ticks");

        slider=new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(false);
        addSlide(slider,"No Track");

        slider=new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setInverted(true);
        addSlide(slider,"Inverted");

        slider=new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlide(slider,"Labels");

        slider=new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);

        Hashtable<Integer, Component> labetTable = new Hashtable<>();
        labetTable.put(0,new JLabel("A"));
        labetTable.put(20,new JLabel("B"));
        labetTable.put(40,new JLabel("C"));
        labetTable.put(60,new JLabel("D"));
        labetTable.put(80,new JLabel("E"));
        labetTable.put(100,new JLabel("F"));

        slider.setLabelTable(labetTable);
        addSlide(slider,"Custom Labels");

        slider=new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(20);

        labetTable = new Hashtable<>();
        labetTable.put(0,new JLabel(new ImageIcon("nine.gif")));
        labetTable.put(20,new JLabel(new ImageIcon("ten.gif")));
        labetTable.put(40,new JLabel(new ImageIcon("jack.gif")));
        labetTable.put(60,new JLabel(new ImageIcon("queen.gif")));
        labetTable.put(80,new JLabel(new ImageIcon("king.gif")));
        labetTable.put(100,new JLabel(new ImageIcon("ace.gif")));

        slider.setLabelTable(labetTable);
        addSlide(slider,"Icon Labels");


        textField=new JTextField();
        add(slidePanel,BorderLayout.CENTER);
        add(textField,BorderLayout.SOUTH);
        pack();


    }

    public void addSlide(JSlider slider,String description)
    {
        slider.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(slider);
        panel.add(new JLabel(description));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy=slidePanel.getComponentCount();
        gbc.anchor=GridBagConstraints.WEST;
        slidePanel.add(panel,gbc);
    }
}
