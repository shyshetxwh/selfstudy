package cn.shyshetxwh.examples;

import java.util.Vector;

/**
 * FileName: BetterVector
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 20:04
 */
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent)
            add(x);
        return absent;
    }
}
