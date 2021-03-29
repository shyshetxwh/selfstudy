package v22swing;

import cn.shyshetxwh.util.Countries;
import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: TextArea
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 19:27
 */
public class TextArea extends JFrame {
    private JButton b=new JButton("Add Data");
    private JButton c=new JButton("Clear Data");
    private JTextArea t=new JTextArea(20,40);
    private Map<String,String> m=new HashMap<>();

    public TextArea() throws HeadlessException {
        m.putAll(Countries.capitals());
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Map.Entry<String, String> me : m.entrySet()) {
                    t.append(me.getKey()+": "+me.getValue()+"\n");
                }
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setText("");
            }
        });
        setLayout(new FlowLayout());
        add(new JScrollPane(t));
        add(b);
        add(c);
    }

    public static void main(String[] args) {
        SwingConsole.run(new TextArea(),475,425);
    }
}
