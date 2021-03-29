package cn.shyshetxwh.Swing.Font;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class FontTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            FontFrame frame = new FontFrame();
            frame.setTitle("FontTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class FontFrame extends JFrame
{
    public FontFrame()
    {
        add(new FontComponent());
        pack();
    }
}

class FontComponent extends JComponent
{
    public static final int DEFAULT_WIDTH=300;
    public static final int DEFAULT_HEIGHT=200;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        String message="Hello World!";
        Font font = new Font("Serif", Font.BOLD, 36);
        g1.setFont(font);

        //得到这个消息的大小
        FontRenderContext context = g1.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(message, context);

        //让这个消息居中显示，先得到文本的左上角坐标
        double x=(getWidth()-bounds.getWidth())/2;
        double y=(getHeight()-bounds.getHeight())/2;

        //通过x和y找到消息的基线
        double ascent=-bounds.getY();
        double baseY=y+ascent;

        //画这个消息
        g1.drawString(message,(int)x,(int)baseY);

        //画基线
        Line2D.Double baseLine = new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY);
        g1.setColor(Color.RED);
        g1.draw(baseLine);

        //画封闭矩形
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        g1.draw(rect);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
