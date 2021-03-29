package cn.shyshetxwh.examples;

import net.jcip.annotations.ThreadSafe;

/**
 * FileName: CasCounter
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/17 0017 8:35
 */
@ThreadSafe
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        }
        while (!value.compareAndSet(v, v + 1));
        return v + 1;
    }
}
