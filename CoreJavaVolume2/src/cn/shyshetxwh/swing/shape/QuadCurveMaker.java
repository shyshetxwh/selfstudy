package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

public class QuadCurveMaker extends ShapeMaker {
    public QuadCurveMaker() {
        super(3);
    }

    @Override
    public Shape makeShape(Point2D[] p) {
        return new QuadCurve2D.Double(p[0].getX(),p[0].getY(),p[1].getX(),p[1].getY(),p[2].getX(),p[2].getY());
    }
}
