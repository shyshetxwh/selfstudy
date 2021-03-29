package cn.shyshetxwh.ScriptCompileAnnotate.buttons2;

import javax.swing.*;
import java.awt.*;

public abstract class ButtonFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

    protected abstract void addEventHandles();

    public ButtonFrame()  {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        panel=new JPanel();
        add(panel);

        yellowButton=new JButton("Yellow");
        blueButton=new JButton("Blue");
        redButton=new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        addEventHandles();
    }
}
