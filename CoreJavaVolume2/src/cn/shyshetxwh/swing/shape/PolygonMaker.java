package cn.shyshetxwh.swing.shape;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class PolygonMaker extends ShapeMaker {
    public PolygonMaker() {
        super(6);
    }

    @Override
    public Shape makeShape(Point2D[] p) {
        GeneralPath path = new GeneralPath();
        path.moveTo((float)p[0].getX(),(float)p[0].getY());
        for (int i = 1; i < p.length; i++) {
            path.lineTo((float)p[i].getX(),(float)p[i].getY());
        }
        path.closePath();
        return path;
    }
}
