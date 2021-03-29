package cn.shyshetxwh.swing.clip;

import javax.swing.*;
import java.awt.*;

public class ClipFrame extends JFrame {
    public ClipFrame()  {
        ClipComponent component = new ClipComponent();
        add(component);
        pack();
    }
}
