package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * FileName: Popup
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 17:00
 */
public class Popup extends JFrame {
    private JPopupMenu popup=new JPopupMenu();
    private JTextField t=new JTextField(10);

    class PopupListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e){
            if (e.isPopupTrigger())
                popup.show(e.getComponent(),e.getX(),e.getY());
        }
    }

    public Popup() throws HeadlessException {
        setLayout(new FlowLayout());
        add(t);
        ActionListener al=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setText(((JMenuItem)e.getSource()).getText());
            }
        };
        JMenuItem m = new JMenuItem("Hither");
        m.addActionListener(al);
        popup.add(m);

        m = new JMenuItem("Yon");
        m.addActionListener(al);
        popup.add(m);

        m = new JMenuItem("Afar");
        m.addActionListener(al);
        popup.add(m);
        popup.addSeparator();

        m = new JMenuItem("Stay here");
        m.addActionListener(al);
        popup.add(m);

        PopupListener pl=new PopupListener();
        addMouseListener(pl);
        t.addMouseListener(pl);

    }

    public static void main(String[] args) {
        SwingConsole.run(new Popup(),300,200);
    }
}
