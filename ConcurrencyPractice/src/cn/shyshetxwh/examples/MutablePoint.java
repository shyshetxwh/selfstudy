package cn.shyshetxwh.examples;

import net.jcip.annotations.NotThreadSafe;

/**
 * FileName: MutablePoint
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 19:14
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
