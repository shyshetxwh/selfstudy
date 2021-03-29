package cn.shyshetxwh.Swing.Draw;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class DrawTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            DrawFrame frame = new DrawFrame();
            frame.setTitle("DrawTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }


}
class DrawFrame extends JFrame
{
    public DrawFrame()
    {
        add(new DrawComponent());
        pack();
    }
}

class DrawComponent extends JComponent
{
    private static final int DEFAULT_WIDTH=400;
    private static final int DEFAULT_HEIGHT=400;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        //开始画一个矩形
        double leftX=100;
        double topY=100;
        double width=200;
        double height=150;
        Rectangle2D.Double rect = new Rectangle2D.Double(leftX, topY, width, height);
        g1.draw(rect);
        //开始画椭圆
        Ellipse2D.Double ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g1.draw(ellipse);
        //开始画线段
        Line2D.Double line = new Line2D.Double(leftX, topY, leftX + width, topY + height);
        g1.draw(line);
        //画一个同心圆
        double radius=150;
        Ellipse2D.Double circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(rect.getCenterX(),rect.getCenterY(),rect.getCenterX()+radius,rect.getCenterY()+radius);
        g1.draw(circle);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
