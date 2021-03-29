package v22swing;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * FileName: TrackEvent
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 23:03
 */
public class TrackEvent extends JFrame {
    private HashMap<String,JTextField> h=new HashMap<>();
    private String[] event = {
            "focusGained", "focusLost", "keyPressed",
            "keyReleased", "keyTyped", "mouseClicked",
            "mouseEntered", "mouseExited", "mousePressed",
            "mouseReleased", "mouseDragged", "mouseMoved"
    };
    private MyButton b1=new MyButton(Color.BLUE,"test1");
    private MyButton b2=new MyButton(Color.RED,"test2");
    
    class MyButton extends JButton{
        void report(String field,String msg){
            h.get(field).setText(msg);
        }
        
        FocusListener fl=new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                report("focusGained",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void focusLost(FocusEvent e) {
                report("focusLost",((JButton)e.getSource()).getText()+e.paramString());
            }
        };
        
        KeyListener kl=new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                report("keyTyped",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                report("keyPressed",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                report("keyReleased",((JButton)e.getSource()).getText()+e.paramString());
            }
        };
        
        MouseListener ml=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                report("mouseClicked",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                report("mousePressed",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                report("mouseReleased",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                report("mouseEntered",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                report("mouseExited",((JButton)e.getSource()).getText()+e.paramString());
            }
        };
        
        MouseMotionListener mml=new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                report("mouseDragged",((JButton)e.getSource()).getText()+e.paramString());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                report("mouseMoved",((JButton)e.getSource()).getText()+e.paramString());
            }
        };

        public MyButton(Color color,String label) {
            super(label);
            setBackground(color);
            addFocusListener(fl);
            addKeyListener(kl);
            addMouseListener(ml);
            addMouseMotionListener(mml);
        }
    }

    public TrackEvent() throws HeadlessException {
        setLayout(new GridLayout(event.length+1,2));
        for (String evt : event) {
            JTextField t = new JTextField();
            add(new JLabel(evt,JLabel.RIGHT));
            add(t);
            h.put(evt,t);
        }
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        SwingConsole.run(new TrackEvent(),700,500);
    }
}
