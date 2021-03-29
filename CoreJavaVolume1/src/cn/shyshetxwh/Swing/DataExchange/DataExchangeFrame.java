package cn.shyshetxwh.Swing.DataExchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataExchangeFrame extends JFrame {
    public static final int TEXT_ROWS=20;
    public static final int TEXT_COLUMNS=40;
    private PasswordChooser pc=null;
    private JTextArea textArea;

    public DataExchangeFrame()
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem connectItem = new JMenuItem("Connect");
        connectItem.addActionListener(new ConnectAction());
        fileMenu.add(connectItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event->System.exit(0));
        fileMenu.add(exitItem);

        textArea=new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        pack();


    }

    private class ConnectAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           if(pc==null)
           {
               pc=new PasswordChooser();
           }
           pc.setUser(new User("yourname",null));

           if(pc.showDialog(DataExchangeFrame.this,"Connect"))
           {
               User u = pc.getUser();
               textArea.append("User name= "+u.getUsername()+",password= "+(new String(u.getPassword()))+"\n");
           }
        }
    }
}
