package cn.shyshetxwh.swing.composite;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class CompositeComponent extends JComponent {
    private int rule;
    private Shape shape1;
    private Shape shape2;
    private float alpha;

    public CompositeComponent() {
        shape1=new Ellipse2D.Double(100,100,150,100);
        shape2=new Rectangle2D.Double(150,150,150,100);
    }

    public void setRule(int rule) {
        this.rule = rule;
        repaint();
    }

    public void setAlpha(float alpha) {
        this.alpha = (float)alpha/100.0F;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gImage = image.createGraphics();
        gImage.setPaint(Color.RED);
        gImage.fill(shape1);

        AlphaComposite composite = AlphaComposite.getInstance(rule, alpha);
        gImage.setComposite(composite);
        gImage.setPaint(Color.BLUE);
        gImage.fill(shape2);

        g2.drawImage(image,null,0,0);
    }
}
