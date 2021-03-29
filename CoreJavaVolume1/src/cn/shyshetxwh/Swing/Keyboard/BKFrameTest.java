package cn.shyshetxwh.Swing.Keyboard;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BKFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            BKFrame frame = new BKFrame();
            frame.setTitle("MyTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}

class BKFrame extends JFrame
{
    private static final int DEFAULT_WIDTH=600;
    private static final int DEFAULT_HEIGHT=400;
    private JPanel BKPanel;

    public BKFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        BKPanel=new JPanel();
        ColorAction redAction = new ColorAction("red", Color.RED);
        ColorAction yellowAction = new ColorAction("yellow", Color.YELLOW);
        ColorAction blueAction = new ColorAction("blue", Color.BLUE);
        makeButton(redAction);
        makeButton(yellowAction);
        makeButton(blueAction);

        add(BKPanel);


        InputMap inputMap = BKPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = BKPanel.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ctrl R"),"panel.red");
        inputMap.put(KeyStroke.getKeyStroke("ctrl Y"),"panel.yellow");
        inputMap.put(KeyStroke.getKeyStroke("ctrl B"),"panel.blue");
        actionMap.put("panel.red",redAction);
        actionMap.put("panel.yellow",yellowAction);
        actionMap.put("panel.blue",blueAction);




    }

    public void makeButton(Action action)
    {
        JButton button = new JButton(action);
        BKPanel.add(button);
    }

    public class ColorAction extends AbstractAction
    {

        public  ColorAction(String name,Color c)
        {
            putValue(Action.NAME,name);
            putValue("color",c);
            putValue(Action.SHORT_DESCRIPTION,"Set panel color to "+name.toLowerCase());
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("color");
            BKPanel.setBackground(c);
        }
    }
}
