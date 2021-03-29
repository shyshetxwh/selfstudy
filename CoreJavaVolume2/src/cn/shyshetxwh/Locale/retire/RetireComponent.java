package cn.shyshetxwh.Locale.retire;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RetireComponent extends JComponent {
    private static final int PANEL_WIDTH=400;
    private static final int PANEL_HEIGHT=200;
    private static final Dimension PREFERRED_SIZE=new Dimension(800,600);
    private RetireInfo info=null;
    private Color colorPre;
    private Color colorGain;
    private Color colorLoss;

    public RetireComponent()
    {
        setSize(PANEL_WIDTH,PANEL_HEIGHT);
    }

    public void setInfo(RetireInfo info) {
        this.info = info;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2= (Graphics2D) g;
        if (info==null)return;

        double minValue=0;
        double maxValue=0;
        int i;
        for (i=info.getCurrentAge();i<=info.getDeathAge();i++)
        {
            double v = info.getBalance(i);
            if (minValue>v) minValue=v;
            if (maxValue<v) maxValue=v;
        }
        if (minValue==maxValue)return;

        int barWidth=getWidth()/(info.getDeathAge()-info.getCurrentAge()+1);
        double scale=getHeight()/(maxValue-minValue);
        for (i=info.getCurrentAge();i<=info.getDeathAge();i++)
        {
            int x1=(i-info.getCurrentAge())*barWidth+1;
            int y1;
            double v = info.getBalance(i);
            int height;
            int yOrgin= (int) (maxValue*scale);
            if (v>0)
            {
                y1= (int) ((maxValue-v)*scale);
                height= yOrgin-y1;
            }
            else
            {
                y1=yOrgin;
                height= (int) (-v*scale);
            }
            if (i<info.getRetireAge())g2.setPaint(colorPre);
            else if (v>=0)g2.setPaint(colorGain);
            else g2.setPaint(colorLoss);
            Rectangle2D.Double bar = new Rectangle2D.Double(x1, y1, barWidth - 2, height);
            g2.fill(bar);
            g2.setPaint(Color.BLACK);
            g2.draw(bar);
        }

    }

    public void setColorPre(Color colorPre) {
        this.colorPre = colorPre;
        repaint();
    }

    public void setColorGain(Color colorGain) {
        this.colorGain = colorGain;
        repaint();
    }

    public void setColorLoss(Color colorLoss) {
        this.colorLoss = colorLoss;
        repaint();
    }

    @Override
    public  Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
