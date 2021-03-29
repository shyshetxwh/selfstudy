package cn.shyshetxwh.swing.book;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Banner implements Printable {
    private String message;
    private double scale;

    public Banner(String message) {
        this.message = message;
    }


    public int getPageCount(Graphics2D g2, PageFormat pf) {
        if (message.equals(""))return 0;
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 72);
        Rectangle2D bounds = f.getStringBounds(message, context);
        scale=pf.getImageableHeight()/bounds.getHeight();
        double width = scale * bounds.getWidth();
        int pages = (int) Math.ceil(width / pf.getImageableWidth());
        return pages;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) {
        Graphics2D g2 = (Graphics2D) g;
        //这里因为有一张封面，所以是>，而不是>=
        if (page>getPageCount(g2,pf))return Printable.NO_SUCH_PAGE;
        g2.translate(pf.getImageableX(),pf.getImageableY());

        drawPage(g2,pf,page);
        return Printable.PAGE_EXISTS;
    }

    private void drawPage(Graphics2D g2, PageFormat pf, int page) {
        if (message.equals(""))return;
        page--;  //把封面去掉

        drawCropMarks(g2,pf);
        g2.clip(new Rectangle2D.Double(0,0,pf.getImageableWidth(),pf.getImageableHeight()));
        g2.translate(-page*pf.getImageableWidth(),0);
        g2.scale(scale,scale);
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 72);
        TextLayout layout = new TextLayout(message, f, context);
        Shape outline = layout.getOutline(AffineTransform.getTranslateInstance(0, layout.getAscent()));
        g2.draw(outline);
    }

    private void drawCropMarks(Graphics2D g2, PageFormat pf) {
        final double C=36;
        double w = pf.getImageableWidth();
        double h = pf.getImageableHeight();
        g2.draw(new Line2D.Double(0, 0, 0, C));
        g2.draw(new Line2D.Double(0, 0, C, 0));
        g2.draw(new Line2D.Double(w, 0, w, C));
        g2.draw(new Line2D.Double(w, 0, w - C, 0));
        g2.draw(new Line2D.Double(0, h, 0, h - C));
        g2.draw(new Line2D.Double(0, h, C, h));
        g2.draw(new Line2D.Double(w, h, w, h - C));
        g2.draw(new Line2D.Double(w, h, w - C, h));
    }
}
