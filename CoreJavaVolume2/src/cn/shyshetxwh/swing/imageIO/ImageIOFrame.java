package cn.shyshetxwh.swing.imageIO;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ImageIOFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    private static Set<String>writeFormats=getWriteFormats();
    private BufferedImage[] images;


    public ImageIOFrame()  {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        JMenu fileMenu = new JMenu("文件");
        JMenuItem openItem = new JMenuItem("打开");
        openItem.addActionListener(event->openFile());
        fileMenu.add(openItem);

        JMenu saveMenu = new JMenu("保存");
        fileMenu.add(saveMenu);
        Iterator<String> iter = writeFormats.iterator();
        while (iter.hasNext())
        {
            final String formatName = iter.next();
            JMenuItem formatItem = new JMenuItem(formatName);
            saveMenu.add(formatItem);
            formatItem.addActionListener(event->saveFile(formatName));
        }

        JMenuItem exitItem = new JMenuItem("退出");
        exitItem.addActionListener(event->System.exit(0));
        fileMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void saveFile(final String formatName) {
        if (images==null)return;
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(formatName);
        ImageWriter writer = iter.next();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        String[] extensions =writer.getOriginatingProvider().getFileSuffixes();
        chooser.setFileFilter(new FileNameExtensionFilter("Image files",extensions));
        int r = chooser.showOpenDialog(this);
        if (r!=JFileChooser.APPROVE_OPTION)return;
        File f = chooser.getSelectedFile();

        try {
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(f);
            writer.setOutput(imageOut);

            writer.write(new IIOImage(images[0],null,null));
            for (int i = 1; i < images.length; i++) {
                IIOImage iioImage = new IIOImage(images[i], null, null);
                if (writer.canInsertImage(i))writer.writeInsert(i,iioImage,null);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        String[] extensions = ImageIO.getReaderFileSuffixes();
        chooser.setFileFilter(new FileNameExtensionFilter("Image files",extensions));
        int r = chooser.showOpenDialog(this);
        if (r!=JFileChooser.APPROVE_OPTION)return;
        File f = chooser.getSelectedFile();
        Box box = Box.createVerticalBox();

        try {
            String name = f.getName();
            String suffix = name.substring(name.lastIndexOf('.') + 1);
            Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
            ImageReader reader = iter.next();
            ImageInputStream imageIn = ImageIO.createImageInputStream(f);
            reader.setInput(imageIn);
            int count = reader.getNumImages(true);
            images=new BufferedImage[count];
            for (int i = 0; i < count; i++) {
                images[i] = reader.read(i);
                box.add(new JLabel(new ImageIcon(images[i])));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,e);
        }

        setContentPane(new JScrollPane(box));
        validate();
    }


    private static Set<String> getWriteFormats() {
        Set<String>writeFormats=new TreeSet<>();
        List<String> formatNames=new ArrayList<>();
        Collections.addAll(formatNames,ImageIO.getWriterFormatNames());
        while(formatNames.size()>0)
        {
            String name = formatNames.iterator().next();
            Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(name);
            ImageWriter writer = iter.next();
            String[] names = writer.getOriginatingProvider().getFormatNames();
            String format=names[0];
            if (format.equals(format.toLowerCase()))format=format.toUpperCase();
            writeFormats.add(format);
            for (String s : names) {
                formatNames.remove(s);
            }

        }
        return writeFormats;
    }



}
