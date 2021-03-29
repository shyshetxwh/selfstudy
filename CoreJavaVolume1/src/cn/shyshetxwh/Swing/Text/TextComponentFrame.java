package cn.shyshetxwh.Swing.Text;

import javax.swing.*;
import java.awt.*;

public class TextComponentFrame extends JFrame{
    private static final int TEXTAREA_ROWS=8;
    private static final int TEXTAREA_COLUMNS=20;

    public TextComponentFrame()
    {
        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel northPanel=new JPanel();
        northPanel.setLayout(new GridLayout(2,2));
        northPanel.add(new JLabel("Username:",SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Password:",SwingConstants.RIGHT));
        northPanel.add(passwordField);

        add(northPanel,BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane,BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JButton insertButton = new JButton("Insert");
        southPanel.add(insertButton);
        insertButton.addActionListener(event->{
            textArea.append("Username: "+textField.getText()+" Password: "+new String(passwordField.getPassword())+"\n");
        });
        add(southPanel,BorderLayout.SOUTH);

        pack();
    }
}
