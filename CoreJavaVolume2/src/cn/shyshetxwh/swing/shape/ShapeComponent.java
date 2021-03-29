package cn.shyshetxwh.swing.shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class ShapeComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE=new Dimension(300,200);
    private Point2D[] points;
    private static Random generator=new Random();
    private static int SIZE=10;
    private int current;
    private ShapeMaker shapeMaker;

    public ShapeComponent()
    {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                for (int i = 0; i < points.length; i++) {
                    double x = points[i].getX() - SIZE / 2;
                    double y = points[i].getY() - SIZE / 2;
                    Rectangle2D.Double r = new Rectangle2D.Double(x, y, SIZE, SIZE);
                    if (r.contains(p))
                    {
                        current=i;
                        return;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                current=-1;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (current==-1)return;
                points[current]=e.getPoint();
                repaint();
            }
        });
        current=-1;
    }

    public void setShapeMaker(ShapeMaker shapeMaker) {
        this.shapeMaker = shapeMaker;
        int n=shapeMaker.getPointCount();
        points=new Point2D[n];
        for (int i = 0; i < n; i++) {
            double x=generator.nextDouble()*getWidth();
            double y=generator.nextDouble()*getHeight();
            points[i]=new Point2D.Double(x,y);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (points==null)return;
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < points.length; i++) {
            double x = points[i].getX() - SIZE / 2;
            double y = points[i].getY() - SIZE / 2;
            g2.fill(new Rectangle2D.Double(x,y,SIZE,SIZE));
        }
        g2.draw(shapeMaker.makeShape(points));
    }

    @Override
    public  Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
