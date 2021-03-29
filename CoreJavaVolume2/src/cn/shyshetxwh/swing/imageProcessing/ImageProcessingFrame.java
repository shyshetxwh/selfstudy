package cn.shyshetxwh.swing.imageProcessing;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageProcessingFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private BufferedImage image;


    public ImageProcessingFrame()  {
        setTitle("ImageProcessingTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                if (image!=null)g.drawImage(image,0,0,null);
            }
        });

        JMenu fileMenu = new JMenu("文件");
        JMenuItem openItem = new JMenuItem("打开");
        openItem.addActionListener(event->openFile());
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenuItem("退出");
        exitItem.addActionListener(event -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("编辑");
        JMenuItem blurItem = new JMenuItem("模糊");
        blurItem.addActionListener(event->{
            float weight=1.0F/9.0F;
            float[] elements = new float[9];
            for (int i = 0; i < elements.length; i++) {
                elements[i]=weight;
            }
            convolve(elements);
        });
        editMenu.add(blurItem);

        JMenuItem sharpenItem = new JMenuItem("锐化");
        sharpenItem.addActionListener(event->{
            float[] elements = { 0.0f, -1.0f, 0.0f, -1.0f, 5.f, -1.0f, 0.0f, -1.0f, 0.0f };
            convolve(elements);
        });
        editMenu.add(sharpenItem);

        JMenuItem brightenItem = new JMenuItem("亮化");
        brightenItem.addActionListener(event->{
            float a=1.1F;
            float b=20.0F;
            RescaleOp op = new RescaleOp(a, b, null);
            filter(op);
        });
        editMenu.add(brightenItem);

        JMenuItem edgeDetectItem = new JMenuItem("边缘检测");
        edgeDetectItem.addActionListener(event->{
            float[] elements = { 0.0f, -1.0f, 0.0f, -1.0f, 4.f, -1.0f, 0.0f, -1.0f, 0.0f };
            convolve(elements);
        });
        editMenu.add(edgeDetectItem);

        JMenuItem negativeItem = new JMenuItem("反色");
        negativeItem.addActionListener(event->{
            short[] negative = new short[256];
            for (int i = 0; i < 256; i++) {
                negative[i]=(short) (255-i);
            }
            ShortLookupTable table = new ShortLookupTable(0, negative);
            LookupOp op = new LookupOp(table,null);
            filter(op);
        });
        editMenu.add(negativeItem);

        JMenuItem rotateItem = new JMenuItem("翻转");
        rotateItem.addActionListener(event->{
            if (image==null)return;
            AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(5), image.getWidth() / 2, image.getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
            filter(op);
        });
        editMenu.add(rotateItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setCurrentDirectory(new File(getClass().getPackage().getName()));
        String[] extensions = ImageIO.getReaderFileSuffixes();
        chooser.setFileFilter(new FileNameExtensionFilter("Image files",extensions));
        int r = chooser.showOpenDialog(this);
        if (r!=JFileChooser.APPROVE_OPTION)return;

        try {
            Image img = ImageIO.read(chooser.getSelectedFile());
            image=new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
            image.getGraphics().drawImage(img,0,0,null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,e);
        }
        repaint();
    }

    private void filter(BufferedImageOp op) {
        if (image==null)return;
        image = op.filter(image, null);
        repaint();
    }

    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);
        ConvolveOp op = new ConvolveOp(kernel);
        filter(op);
    }
}
