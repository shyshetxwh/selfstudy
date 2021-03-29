package cn.shyshetxwh.examples;

/**
 * FileName: WaitNotifyBoundedBuffer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 20:13
 */
public class WaitNotifyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public WaitNotifyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull())
            wait();
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty())
            wait();
        V v = doTake();
        notifyAll();
        return v;
    }
}
