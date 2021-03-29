package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class RoundRectangleMaker extends ShapeMaker {
    public RoundRectangleMaker() {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p) {
        RoundRectangle2D.Double s = new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20);
        s.setFrameFromDiagonal(p[0],p[1]);
        return s;
    }
}
