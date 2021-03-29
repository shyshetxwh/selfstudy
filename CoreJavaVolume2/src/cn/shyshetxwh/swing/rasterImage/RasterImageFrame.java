package cn.shyshetxwh.swing.rasterImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class RasterImageFrame extends JFrame {
    private static final int IMAGE_WIDTH = 400;
    private static final int IMAGE_HEIGHT = 400;
    private static final double XMIN = -2;
    private static final double XMAX = 2;
    private static final double YMIN = -2;
    private static final double YMAX = 2;
    private static final int MAX_ITERATIONS = 16;

    public RasterImageFrame()  {
        BufferedImage image=makeMandelbrot(IMAGE_WIDTH,IMAGE_HEIGHT);
        add(new JLabel(new ImageIcon(image)));
        pack();
    }

    private BufferedImage makeMandelbrot(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //获得访问和修改该缓存图像的像素栅格
        WritableRaster raster = image.getRaster();
        //返回被缓存图片的颜色模型
        ColorModel model = image.getColorModel();

        Color fractalColor=Color.RED;
        int rgb = fractalColor.getRGB();
        Object colorData = model.getDataElements(rgb, null);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double a=XMIN+i*(XMAX-XMIN)/width;
                double b=YMIN+j*(YMAX-YMIN)/height;
                if (!escapesToInfinity(a,b))raster.setDataElements(i,j,colorData);
            }
        }
        return image;
    }

    private boolean escapesToInfinity(double a, double b) {
        double x=0.0;
        double y=0.0;
        int iterations=0;

        while(x<=2&&y<=2&&iterations<MAX_ITERATIONS)
        {
            double xnew = x * x - y * y + a;
            double ynew = 2 * x * y + b;
            x=xnew;
            y=ynew;
            iterations++;
        }
        return x>2||y>2;
    }
}
