package cn.shyshetxwh.Swing.Dialog;

import javax.swing.*;

public class DialogFrame extends JFrame {
    public static final int DEFAULT_WIDTH=300;
    public static final int DEFAULT_HEIGHT=200;
    private AboutDialog dialog;

    public DialogFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_WIDTH);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(event->{
            if(dialog==null)
            {
                dialog=new AboutDialog(DialogFrame.this);
            }
            dialog.setVisible(true);
        });
        fileMenu.add(aboutItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(evevt->System.exit(0));
        fileMenu.add(exitItem);
    }
}
