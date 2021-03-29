package cn.shyshetxwh.swing.stroke;

import javax.swing.*;
import java.awt.*;

public class StrokeFrame extends JFrame {
    private StrokeComponent canvas;
    private JPanel buttonPanel;

    public StrokeFrame()  {
        canvas=new StrokeComponent();
        add(canvas,BorderLayout.CENTER);

        buttonPanel=new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        add(buttonPanel,BorderLayout.NORTH);

        ButtonGroup capGroup = new ButtonGroup();
        makeCapButton("平头样式",BasicStroke.CAP_BUTT,capGroup);
        makeCapButton("圆头样式",BasicStroke.CAP_ROUND,capGroup);
        makeCapButton("方头样式",BasicStroke.CAP_SQUARE,capGroup);

        ButtonGroup joinGroup = new ButtonGroup();
        makeJoinButton("斜尖样式",BasicStroke.JOIN_MITER,joinGroup);
        makeJoinButton("斜连接",BasicStroke.JOIN_BEVEL,joinGroup);
        makeJoinButton("圆连接",BasicStroke.JOIN_ROUND,joinGroup);

        ButtonGroup dashGroup = new ButtonGroup();
        makeDashButton("实线",false,dashGroup);
        makeDashButton("虚线",true,dashGroup);
    }

    private void makeDashButton(String label, final boolean style, ButtonGroup group) {
        //让第一个按钮被默认选中
        boolean selected=group.getButtonCount()==0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(event->canvas.setDash(style));
        pack();
    }

    private void makeJoinButton(String label, final int style, ButtonGroup group) {
        //让第一个按钮被默认选中
        boolean selected=group.getButtonCount()==0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(event->canvas.setJoin(style));
        pack();
    }

    private void makeCapButton(String label, final int style, ButtonGroup group) {
        //让第一个按钮被默认选中
        boolean selected=group.getButtonCount()==0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(event->canvas.setCap(style));
        pack();
    }
}
