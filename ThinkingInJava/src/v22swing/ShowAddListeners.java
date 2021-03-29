package v22swing;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName: ShowAddListeners
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 22:31
 */
public class ShowAddListeners extends JFrame {
    private JTextField name=new JTextField(25);
    private JTextArea results=new JTextArea(40,65);
    private static Pattern addListener=Pattern.compile("(add\\w+?Listener\\(.*?\\))");
    private static Pattern qualifier=Pattern.compile("\\w+\\.");
    class NameL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String nm = name.getText().trim();
            if (nm.length()==0){
                results.setText("No Match");
                return;
            }
            Class<?> kind;
            try {
                kind=Class.forName("javax.swing."+nm);
            } catch (ClassNotFoundException e1) {
                results.setText("No Match");
                return;
            }
            Method[] methods = kind.getMethods();
            results.setText("");
            for (Method method : methods) {
                Matcher matcher = addListener.matcher(method.toString());
                if (matcher.find())
                    results.append(qualifier.matcher(matcher.group(1)).replaceAll("")+"\n");
            }
        }
    }

    public ShowAddListeners() throws HeadlessException {
        NameL nameListener = new NameL();
        name.addActionListener(nameListener);
        JPanel top = new JPanel();
        top.add(new JLabel("Swing class name (press Enter):"));
        top.add(name);
        add(BorderLayout.NORTH,top);
        add(new JScrollPane(results));

        name.setText("JTextArea");
        nameListener.actionPerformed(new ActionEvent("",0,""));
    }

    public static void main(String[] args) {
        SwingConsole.run(new ShowAddListeners(),500,400);
    }
}
