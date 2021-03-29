package cn.shyshetxwh.security.permissions;

import javax.swing.*;
import java.awt.*;

public class PermissionTestFrame extends JFrame {
    private JTextField textField;
    private WordCheckTextArea textArea;
    private static final int TEXT_ROWS = 20;
    private static final int TEXT_COLUMNS = 60;

    public PermissionTestFrame()
    {
        textField=new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(textField);
        JButton openButton = new JButton("Insert");
        panel.add(openButton);
        openButton.addActionListener(event->insertWords(textField.getText()));

        add(panel, BorderLayout.NORTH);

        textArea=new WordCheckTextArea();
        textArea.setRows(TEXT_ROWS);
        textArea.setColumns(TEXT_COLUMNS);
        add(new JScrollPane(textArea),BorderLayout.SOUTH);
        pack();
    }

    private void insertWords(String words) {
        try {
            textArea.append(words+"\n");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(this,"输入敏感");
            e.printStackTrace();
        }
    }


}

class WordCheckTextArea extends JTextArea
{
    public void append(String text)
    {
        WordCheckPermission p = new WordCheckPermission(text, "insert");
        SecurityManager manager = System.getSecurityManager();
        if (manager!=null)manager.checkPermission(p);
        super.append(text);
    }
}
