package cn.shyshetxwh.Swing.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MouseComponent extends JComponent {
    private static final int DEFAULT_WIDTH=600;
    private static final int DEFAULT_HEIGHT=400;
    private static final int SIDELENGTH=20;

    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    public MouseComponent()
    {
        squares=new ArrayList<Rectangle2D>();
        current=null;
        addMouseListener(new MouseHandle());
        addMouseMotionListener(new MouseMotionHandle());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        for (Rectangle2D square : squares) {
            g1.draw(square);
        }
    }

    public Rectangle2D find(Point2D p)
    {
        for (Rectangle2D square : squares) {
            if(square.contains(p))
            {
                return square;
            }
        }
        return null;
    }

    public void add(Point2D p)
    {
        double x = p.getX();
        double y = p.getY();
        current=new Rectangle2D.Double(x-SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH,SIDELENGTH);
        squares.add(current);
        repaint();
    }

    public void remove(Rectangle2D rect)
    {
        if(rect==null)
        {
            return;
        }
        if(rect==current)
        {
            current=null;
        }
        squares.remove(rect);
        repaint();
    }

    private class MouseHandle extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
            current=find(e.getPoint());
            if(current==null)
            {
                add(e.getPoint());
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            current=find(e.getPoint());
            if(current!=null&&e.getClickCount()>=2)
            {
                remove(current);
            }
        }
    }

    private class MouseMotionHandle implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent e) {
            if(current!=null)
            {
                int x = e.getX();
                int y = e.getY();

                current.setFrame(x-SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH,SIDELENGTH);
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(find(e.getPoint())==null)
            {
                setCursor(Cursor.getDefaultCursor());
            }
            else
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

        }
    }
}
