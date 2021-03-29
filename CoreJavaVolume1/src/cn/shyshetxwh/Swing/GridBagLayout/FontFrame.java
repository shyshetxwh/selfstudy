package cn.shyshetxwh.Swing.GridBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FontFrame extends JFrame {
    private JComboBox<String>face;
    private JComboBox<Integer>size;
    private JCheckBox bold;
    private JCheckBox italic;
    private JTextArea sample;

    public static final int TEXT_ROWS=10;
    public static final int TEXT_COLUMNS=20;

    public FontFrame()
    {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        ActionListener listener=event->{
            updateSample();
        };

        JLabel faceLabel = new JLabel("Face: ");
        face=new JComboBox<>(new String[]{"Serif","SanSerif","Monospaced","Dialog","DialogInput"});
        face.addActionListener(listener);

        JLabel sizeLabel = new JLabel("Size: ");
        size=new JComboBox<>(new Integer[]{8,10,12,15,18,24,36,48});
        size.addActionListener(listener);

        bold=new JCheckBox("Bold");
        bold.addActionListener(listener);
        italic=new JCheckBox("Italic");
        italic.addActionListener(listener);

        sample=new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        sample.setText("The quick brown fox jumps over the lazy dog.");
        sample.setEditable(false);
        sample.setLineWrap(true);
        sample.setBorder(BorderFactory.createEtchedBorder());

        add(faceLabel,new GBC(0,0).setAnchor(GridBagConstraints.EAST));
        add(face,new GBC(1,0).setFill(GridBagConstraints.HORIZONTAL).setWeight(100,0).setInsets(1));
        add(sizeLabel,new GBC(0,1).setAnchor(GridBagConstraints.EAST));
        add(size,new GBC(1,1).setFill(GridBagConstraints.HORIZONTAL).setWeight(100,0).setInsets(1));
        add(bold,new GBC(0,2,2,1).setAnchor(GridBagConstraints.CENTER).setWeight(100,100));
        add(italic,new GBC(0,3,2,1).setAnchor(GridBagConstraints.CENTER).setWeight(100,100));
        add(sample,new GBC(2,0,1,4).setFill(GridBagConstraints.BOTH).setWeight(100,100));
        pack();


    }

    private void updateSample() {
        String fontFace = (String) face.getSelectedItem();
        Integer fontSize = size.getItemAt(size.getSelectedIndex());
        int fontStyle=(bold.isSelected()?Font.BOLD:0)+(italic.isSelected()?Font.ITALIC:0);
        sample.setFont(new Font(fontFace,fontStyle,fontSize));
        sample.repaint();
    }
}
