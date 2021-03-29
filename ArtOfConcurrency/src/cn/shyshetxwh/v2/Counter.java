package cn.shyshetxwh.v2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FileName: Counter
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/9 0009 9:38
 */
public class Counter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;

    public void safeCount() {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if (suc)
                break;
        }
    }

    public void count() {
        i++;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public int getI() {
        return i;
    }
}
