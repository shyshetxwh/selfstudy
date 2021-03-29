package cn.shyshetxwh.examples;

import net.jcip.annotations.ThreadSafe;

/**
 * FileName: SimulatedCAS
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/17 0017 8:30
 */
@ThreadSafe
public class SimulatedCAS {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectValue)
            value = newValue;
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectValue, int newValue) {
        return (expectValue == compareAndSwap(expectValue, newValue));
    }
}
