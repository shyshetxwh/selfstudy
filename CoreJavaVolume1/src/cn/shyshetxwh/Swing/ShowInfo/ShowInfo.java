package cn.shyshetxwh.Swing.ShowInfo;

import javax.swing.*;
import java.awt.*;

public class ShowInfo {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->
        {
            ShowInfoFrame frame = new ShowInfoFrame();
            frame.setTitle("ShowInfo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }


}

class ShowInfoFrame extends JFrame
{
    public ShowInfoFrame() throws HeadlessException {
        add(new ShowInfoComponent());
        pack();
    }
}

class ShowInfoComponent extends JComponent
{
    private static final int MESSAGE_X=75;
    private static final int MESSAGE_Y=100;
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;

    public void paintComponent(Graphics g)
    {
        g.drawString("Hello World",MESSAGE_X,MESSAGE_Y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
