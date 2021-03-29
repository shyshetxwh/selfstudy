package cn.shyshetxwh.swing.book;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class CoverPage implements Printable {
    private String title;

    public CoverPage(String title) {
        this.title = title;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page>=1)return Printable.NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.BLACK);
        g2.translate(pf.getImageableX(),pf.getImageableY());
        FontRenderContext context = g2.getFontRenderContext();
        Font f = g2.getFont();
        TextLayout layout = new TextLayout(title, f, context);
        float ascent = layout.getAscent();
        g2.drawString(title,0,ascent);
        return Printable.PAGE_EXISTS;
    }
}
