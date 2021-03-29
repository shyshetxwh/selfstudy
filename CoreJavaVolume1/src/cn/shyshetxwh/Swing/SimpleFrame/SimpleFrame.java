package cn.shyshetxwh.Swing.SimpleFrame;

import javax.swing.*;
import java.awt.*;

public class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;
    public SimpleFrame() throws HeadlessException {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

}
