package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RectangleMaker extends ShapeMaker {
    public RectangleMaker() {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p) {
        Rectangle2D.Double s = new Rectangle2D.Double();
        s.setFrameFromDiagonal(p[0],p[1]);
        return s;
    }
}
