package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LineMaker extends ShapeMaker {
    public LineMaker() {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p) {
        return new Line2D.Double(p[0],p[1]);
    }
}
