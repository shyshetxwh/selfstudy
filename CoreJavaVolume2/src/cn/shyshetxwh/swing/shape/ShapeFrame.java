package cn.shyshetxwh.swing.shape;

import javax.swing.*;
import java.awt.*;

public class ShapeFrame extends JFrame {
    public ShapeFrame()  {
        ShapeComponent component = new ShapeComponent();
        add(component,BorderLayout.CENTER);
        JComboBox<ShapeMaker> comboBox = new JComboBox<>();
        comboBox.addItem(new LineMaker());
        comboBox.addItem(new RectangleMaker());
        comboBox.addItem(new RoundRectangleMaker());
        comboBox.addItem(new EllipseMaker());
        comboBox.addItem(new ArcMaker());
        comboBox.addItem(new PolygonMaker());
        comboBox.addItem(new QuadCurveMaker());
        comboBox.addItem(new CubicCurveMaker());
        comboBox.addActionListener(event->{
            ShapeMaker shapeMaker = comboBox.getItemAt(comboBox.getSelectedIndex());
            component.setShapeMaker(shapeMaker);
        });
        add(comboBox,BorderLayout.NORTH);
        component.setShapeMaker((ShapeMaker)comboBox.getItemAt(0));
        pack();

    }
}
