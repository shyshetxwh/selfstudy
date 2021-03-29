package cn.shyshetxwh.swing.print;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintComponent extends JComponent implements Printable {
    private static final Dimension PREFERRED_SIZE = new Dimension(300, 300);

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawPage(g2);
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page>=1)return Printable.NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(),pf.getImageableY());
        g2.draw(new Rectangle2D.Double(0,0,pf.getImageableWidth(),pf.getImageableHeight()));

        drawPage(g2);
        return Printable.PAGE_EXISTS;
    }

    public void drawPage(Graphics2D g2)
    {
        FontRenderContext context = g2.getFontRenderContext();
        Font font = new Font("Serif", Font.PLAIN, 72);
        TextLayout layout = new TextLayout("Hello", font, context);
        Shape outline = layout.getOutline(AffineTransform.getTranslateInstance(0, 72));
        GeneralPath clipShape = new GeneralPath();
        clipShape.append(outline,false);

        layout = new TextLayout("World", font, context);
        outline = layout.getOutline(AffineTransform.getTranslateInstance(0, 144));
        clipShape.append(outline, false);

        g2.draw(clipShape);
        g2.clip(clipShape);

        final int NLINES=50;
        Point2D.Double p = new Point2D.Double(0, 0);
        for (int i = 0; i < NLINES; i++) {
            double x = (2 * getWidth() * i) / NLINES;
            double y = (2 * getHeight() * (NLINES - 1 - i)) / NLINES;
            Point2D.Double q = new Point2D.Double(x, y);
            g2.draw(new Line2D.Double(p,q));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
