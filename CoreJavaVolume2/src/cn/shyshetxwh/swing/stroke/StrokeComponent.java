package cn.shyshetxwh.swing.stroke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class StrokeComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE=new Dimension(400,400);
    private static int SIZE=10;

    private Point2D[] points;
    private int current;
    private float width;
    private int cap;
    private int join;
    private boolean dash;

    public StrokeComponent() {
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent event)
            {
                Point p = event.getPoint();
                for (int i = 0; i < points.length; i++)
                {
                    double x = points[i].getX() - SIZE / 2;
                    double y = points[i].getY() - SIZE / 2;
                    Rectangle2D.Double r = new Rectangle2D.Double(x, y, SIZE, SIZE);
                    if (r.contains(p))
                    {
                        current = i;
                        return;
                    }
                }
            }

            public void mouseReleased(MouseEvent event)
            {
                current = -1;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent event)
            {
                if (current == -1) return;
                points[current] = event.getPoint();
                repaint();
            }
        });

        points=new Point2D[3];
        points[0]=new Point2D.Double(200,100);
        points[1]=new Point2D.Double(100,200);
        points[2]=new Point2D.Double(200,200);
        current=-1;
        width=8.0F;
    }

    public void setCap(int cap) {
        this.cap = cap;
        repaint();
    }

    public void setJoin(int join) {
        this.join = join;
        repaint();
    }

    public void setDash(boolean dash) {
        this.dash = dash;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GeneralPath path = new GeneralPath();
        path.moveTo((float)points[0].getX(),(float)points[0].getY());
        for (int i =1; i < points.length; i++) {
            path.lineTo((float)points[i].getX(),(float)points[i].getY());
        }
        BasicStroke stroke;
        if (dash)
        {
            //用度数表示的角度，如果小于这个角度，斜尖连接将呈现为斜连接
            float miterLimit=10.0F;
            //虚线笔画的填充部分和空白部分交替出现的一组长度
            float[] dashPattern={10F, 10F, 10F, 10F, 10F, 10F, 30F, 10F, 30F, 10F, 30F, 10F,
                    10F, 10F, 10F, 10F, 10F, 30F};
            float dashPhase=0;
            stroke=new BasicStroke(width,cap,join,miterLimit,dashPattern,dashPhase);

        }
        else stroke=new BasicStroke(width,cap,join);
        g2.setStroke(stroke);
        g2.draw(path);
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
