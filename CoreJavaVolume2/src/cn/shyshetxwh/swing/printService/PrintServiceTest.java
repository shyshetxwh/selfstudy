package cn.shyshetxwh.swing.printService;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintServiceTest {
    private static int IMAGE_WIDTH=300;
    private static int IMAGE_HEIGHT=300;


    public static void draw(Graphics2D g2)
    {
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 72);
        GeneralPath clipShape = new GeneralPath();

        TextLayout layout = new TextLayout("Hello", f, context);
        Shape outline = layout.getOutline(AffineTransform.getTranslateInstance(0, 72));
        clipShape.append(outline,false);

        layout=new TextLayout("World",f,context);
        outline=layout.getOutline(AffineTransform.getTranslateInstance(0,144));
        clipShape.append(outline,false);

        g2.draw(clipShape);
        g2.clip(clipShape);

        final int NLINES = 50;
        Point2D.Double p = new Point2D.Double(0, 0);
        for (int i = 0; i < NLINES; i++)
        {
            double x = (2 * IMAGE_WIDTH * i) / NLINES;
            double y = (2 * IMAGE_HEIGHT * (NLINES - 1 - i)) / NLINES;
            Point2D.Double q = new Point2D.Double(x, y);
            g2.draw(new Line2D.Double(p, q));
        }
    }

    public static void main(String[] args) throws IOException, PrintException {
        String fileName="out.ps";
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        String mimeType="application/postscript";
        StreamPrintServiceFactory[] factories = StreamPrintServiceFactory.lookupStreamPrintServiceFactories(flavor, mimeType);
        FileOutputStream out = new FileOutputStream(fileName);
        if (factories.length>0)
        {
            StreamPrintService service = factories[0].getPrintService(out);
            SimpleDoc doc=new SimpleDoc(new Printable() {
                @Override
                public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                    if (page>=1)return Printable.NO_SUCH_PAGE;
                    else
                    {
                        double sf1=pf.getImageableWidth()/(IMAGE_WIDTH+1);
                        double sf2=pf.getImageableHeight()/(IMAGE_HEIGHT+1);
                        double scale=Math.min(sf1,sf2);
                        Graphics2D g2 = (Graphics2D) g;
                        g2.translate((pf.getWidth() - pf.getImageableWidth()) / 2,
                                (pf.getHeight() - pf.getImageableHeight()) / 2);
                        g2.scale(scale,scale);

                        draw(g2);
                        return Printable.PAGE_EXISTS;
                    }
                }
            },flavor,null);
            DocPrintJob job = service.createPrintJob();
            HashPrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            job.print(doc,attributes);
        }
        else
        {
            System.out.println("No factories for "+mimeType);
        }


    }
}
