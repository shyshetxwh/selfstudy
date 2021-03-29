package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.*;

public class ArcMaker extends ShapeMaker {
    public ArcMaker() {
        super(4);
    }

    @Override
    public Shape makeShape(Point2D[] p) {
        double centerX=(p[0].getX()+p[1].getX())/2;
        double centerY=(p[0].getY()+p[1].getY())/2;
        double width=Math.abs(p[1].getX()-p[0].getX());
        double height=Math.abs(p[1].getY()-p[0].getY());

        //弧线的起始角度，经过转换过的角度，取值范围为-180°到180°
        double skewedStartAngle=Math.toDegrees(Math.atan2(-(p[2].getY()-centerY)*width,(p[2].getX()-centerX)*height));
        //弧线的结束角度，也是经过转化的角度，取值范围为-180°到180°
        double skewedEndAngle=Math.toDegrees(Math.atan2(-(p[3].getY()-centerY)*width,(p[3].getX()-centerX)*height));
        //跨越角度，等于结束角度和起始角度之差
        double skewedAngleDifference=skewedEndAngle-skewedStartAngle;
        //如果起始角和角差为负值，则加上360
        skewedStartAngle=skewedStartAngle<0?skewedStartAngle+360:skewedStartAngle;
        skewedAngleDifference=skewedAngleDifference<0?skewedAngleDifference+360:skewedAngleDifference;
        Arc2D.Double s = new Arc2D.Double(0, 0, 0, 0, skewedStartAngle, skewedAngleDifference, Arc2D.OPEN);
        s.setFrameFromDiagonal(p[0],p[1]);

        GeneralPath path = new GeneralPath();
        path.append(s,false);
        Rectangle2D.Double r = new Rectangle2D.Double();
        r.setFrameFromDiagonal(p[0],p[1]);
        path.append(r,false);

        Point2D.Double center = new Point2D.Double(centerX, centerY);
        path.append(new Line2D.Double(center,p[2]),false);
        path.append(new Line2D.Double(center,p[3]),false);
        return path;

    }
}
