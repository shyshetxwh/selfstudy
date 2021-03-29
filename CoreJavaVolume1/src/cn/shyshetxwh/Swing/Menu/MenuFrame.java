package cn.shyshetxwh.Swing.Menu;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;
    private Action saveAction;
    private Action saveAsAction;
    private JCheckBoxMenuItem readonlyItem;
    private JPanel toolBarPanel;

    class TestAction extends AbstractAction
    {
        public TestAction(String name)
        {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(getValue(Action.NAME)+" Selected.");
        }
    }

    public MenuFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new TestAction("New"));

        JMenuItem openItem = fileMenu.add(new TestAction("Open"));
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));

        fileMenu.addSeparator();

        saveAction=new TestAction("Save");
        JMenuItem saveItem = fileMenu.add(saveAction);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        saveAsAction=new TestAction("Save As");
        fileMenu.add(saveAsAction);

        fileMenu.addSeparator();

        fileMenu.add(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu editMenu = new JMenu("Edit");

        TestAction cutAction = new TestAction("Cut");
        cutAction.putValue(Action.SMALL_ICON,new ImageIcon("cut.gif"));
        editMenu.add(cutAction);

        TestAction copyAction = new TestAction("Copy");
        copyAction.putValue(Action.SMALL_ICON,new ImageIcon("copy.gif"));
        editMenu.add(copyAction);

        TestAction pasteAction = new TestAction("Paste");
        pasteAction.putValue(Action.SMALL_ICON,new ImageIcon("paste.gif"));
        editMenu.add(pasteAction);

        editMenu.addSeparator();

        JMenu optionMenu = new JMenu("Option");

        readonlyItem = new JCheckBoxMenuItem("Read-only");
        readonlyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean saveOK = !readonlyItem.isSelected();
                saveAction.setEnabled(saveOK);
                saveAsAction.setEnabled(saveOK);
            }
        });
        optionMenu.add(readonlyItem);
        optionMenu.addSeparator();

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
        insertItem.setSelected(true);
        JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
        buttonGroup.add(insertItem);
        buttonGroup.add(overtypeItem);
        optionMenu.add(insertItem);
        optionMenu.add(overtypeItem);

        editMenu.add(optionMenu);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');

        JMenuItem indexItem = new JMenuItem("Index");
        indexItem.setMnemonic('I');
        helpMenu.add(indexItem);

        TestAction aboutAction = new TestAction("About");
        aboutAction.putValue(Action.MNEMONIC_KEY,new Integer('A'));
        helpMenu.add(aboutAction);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(cutAction);
        popupMenu.add(copyAction);
        popupMenu.add(pasteAction);

        JPanel panel = new JPanel();
        panel.setComponentPopupMenu(popupMenu);
        add(panel);

        toolBarPanel = new JPanel();

        add(toolBarPanel, BorderLayout.CENTER);

        JToolBar toolBar = new JToolBar();
        toolBar.add(new ColorAction("red",Color.RED));
        add(toolBar,BorderLayout.NORTH);


    }

    public class ColorAction extends AbstractAction
    {

        public  ColorAction(String name, Color c)
        {
            putValue(Action.NAME,name);
            putValue("color",c);
            putValue(Action.SHORT_DESCRIPTION,"Set panel color to "+name.toLowerCase());
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("color");
            toolBarPanel.setBackground(c);

        }
    }
}
