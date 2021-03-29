package cn.shyshetxwh.examples;

import java.util.concurrent.CountDownLatch;

/**
 * FileName: ValueLatch
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/15 0015 19:06
 */
public class ValueLatch<T> {
    private T value;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return (done.getCount() == 0);
    }

    public synchronized void setValue(T value) {
        if (!isSet()) {
            this.value = value;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
