package cn.shyshetxwh.swing.clip;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class ClipComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE=new Dimension(800,200);

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        FontRenderContext context = g2.getFontRenderContext();
        Font font=new Font("华文琥珀",Font.PLAIN,120);
        TextLayout layout = new TextLayout("Shyshetxwh", font, context);
        AffineTransform transform = AffineTransform.getTranslateInstance(0, 100);
        Shape outline = layout.getOutline(transform);
        GeneralPath path = new GeneralPath();
        path.append(outline,false);

        g2.setClip(path);
        Point2D.Double p = new Point2D.Double(0, 0);
        for (int i = 0; i < 20; i++) {
            Point2D.Double q = new Point2D.Double(getWidth(), getHeight() - i * 10);
            g2.draw(new Line2D.Double(p,q));

        }
        for (int i = 0; i < 20; i++) {
            Point2D.Double q = new Point2D.Double(getWidth()-i*40, getHeight());
            g2.draw(new Line2D.Double(p,q));

        }
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
