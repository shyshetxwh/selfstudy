package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class EllipseMaker extends ShapeMaker {
    public EllipseMaker() {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p) {
        Ellipse2D.Double s = new Ellipse2D.Double();
        s.setFrameFromDiagonal(p[0],p[1]);
        return s;
    }
}
