package cn.shyshetxwh.ScriptCompileAnnotate.buttons3;

import cn.shyshetxwh.ScriptCompileAnnotate.runtimeAnnotations.ActionListenerFor;
import cn.shyshetxwh.ScriptCompileAnnotate.runtimeAnnotations.ActionListenerInstaller;

import javax.swing.*;
import java.awt.*;

public class ButtonFrame extends JFrame{
    private final static int DEFAULT_WIDTH=300;
    private final static int DEFAULT_HEIGHT=200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

    public ButtonFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        panel=new JPanel();
        add(panel);

        yellowButton=new JButton("Yellow");
        blueButton=new JButton("Blue");
        redButton=new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        ActionListenerInstaller.processAnnotations(this);
    }

    @ActionListenerFor(source = "yellowButton")
    public void yellowBackground()
    {
        panel.setBackground(Color.YELLOW);
    }

    @ActionListenerFor(source = "blueButton")
    public void blueBackground()
    {
        panel.setBackground(Color.BLUE);
    }

    @ActionListenerFor(source = "redButton")
    public void redBackground()
    {
        panel.setBackground(Color.RED);
    }


}
