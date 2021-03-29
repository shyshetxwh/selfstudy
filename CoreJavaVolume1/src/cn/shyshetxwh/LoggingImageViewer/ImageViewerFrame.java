package cn.shyshetxwh.LoggingImageViewer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=400;
    private JLabel label;
    private static Logger logger=Logger.getLogger("cn.shyshetxwh.LoggingImageViewer");

    public ImageViewerFrame()
    {
        //把方法类名和方法记录到日志中
        logger.entering("ImageViewerFrame","<init>");
        //设置框架的大小
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        //设置菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //给菜单栏添加文件菜单
        JMenu menu = new JMenu("文件");
        menuBar.add(menu);

        //文件菜单添加打开和退出项目
        JMenuItem openItem = new JMenuItem("打开");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("退出");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting.");
                System.exit(0);
            }
        });

        label=new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame","<init>");
    }

    private class FileOpenListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageViewerFrame.FileOpenListener","actionPerformed");
            //设置文件选择界面
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            //设置文件过滤器
            chooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif")||f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "GIF Images";
                }
            });

            //显示文件显示界面
            int r = chooser.showOpenDialog(ImageViewerFrame.this);
            //判断文件选择
            if(r==JFileChooser.APPROVE_OPTION)
            {
                String path = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE,"Reading file {0}",path);
                label.setIcon(new ImageIcon(path));
            }
            else
            {
                logger.fine("File open dialog canceled.");
            }
            logger.exiting("ImageViewerFrame.FileOpenListener","actionPerformed");
        }
    }


}
