package cn.shyshetxwh.Swing.Button;



import javax.swing.*;
import java.awt.*;

public class ButtonFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ButtonFrame frame = new ButtonFrame();
            frame.setTitle("ButtonAndKeyboardTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}

class ButtonFrame extends JFrame
{
    private static final int DEFAULT_WIDTH=600;
    private static final int DEFAULT_HEIGHT=400;
    private JPanel BKPanel;

    public ButtonFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        BKPanel=new JPanel();
        makeButton("red-mouse",Color.RED);
        makeButton("yellow-mouse",Color.YELLOW);
        makeButton("blue-mouse",Color.BLUE);

        add(BKPanel);




    }

    public void makeButton(String name,Color backgroundColor)
    {
        JButton button = new JButton(name);
        BKPanel.add(button);
        button.addActionListener(event->{
            BKPanel.setBackground(backgroundColor);
        });
    }


}
