package cn.shyshetxwh.Swing.FileChooser;




import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class FileChooserFrame extends JFrame{
    public static final int DEFAULT_WIDTH=300;
    public static final int DEFAULT_HEIGHT=400;
    private JLabel label;
    private JFileChooser fileChooser;

    public FileChooserFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);

        openItem.addActionListener(event->{
            fileChooser.setCurrentDirectory(new File("."));
            int result=fileChooser.showOpenDialog(FileChooserFrame.this);
            if(result== JFileChooser.APPROVE_OPTION)
            {
                String name = fileChooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));
                pack();
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(event->System.exit(0));

        label=new JLabel();
        add(label);
        fileChooser=new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "gif");
        fileChooser.setFileFilter(filter);

        fileChooser.setAccessory(new ImagePreviewer(fileChooser));

        fileChooser.setFileView(new FileIconView(filter, new ImageIcon("palette.gif")));


    }
}
