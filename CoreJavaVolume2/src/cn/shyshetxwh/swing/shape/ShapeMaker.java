package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class ShapeMaker {
    private int pointCount;

    public ShapeMaker(int pointCount) {
        this.pointCount = pointCount;
    }

    public int getPointCount() {
        return pointCount;
    }

    public abstract Shape makeShape(Point2D[] p);

    @Override
    public String toString() {
        return getClass().getName();
    }
}
